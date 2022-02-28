package com.devops.developers.customer.service;

import com.devops.developers.customer.dao.UserDao;
import com.devops.developers.customer.entity.Customer;
import com.devops.developers.customer.entity.Role;
import com.devops.developers.customer.customerdetail.CustomerDetailImpl;
import com.devops.developers.dto.CustomerDto;
import com.devops.developers.dto.RoleDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import  org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Optional;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService, CustomerService {


    private final UserDao userDao;

    private final RoleService roleService;

    private final ModelMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public CustomerDetailsServiceImpl(UserDao userDao, ModelMapper mapper, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userDao = userDao;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService=roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer=userDao.findCustomerByUsername(username);
        CustomerDetailImpl customerDetail=customer.map(c -> { return new CustomerDetailImpl(c);})
                .orElseThrow(()-> new BadCredentialsException("bad credentials"));
        return  customerDetail;
    }

    @Override
    public CustomerDto getCustomerByName(String name) {
        Optional<Customer> customer=this.userDao.findCustomerByUsername(name);
        if(!customer.isEmpty()){
            return mapper.map(customer, CustomerDto.class);
        }
        return null;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto){
        Customer customer=mapper.map(customerDto,Customer.class);
        customer.setEnabled(true);
        customer.setRoles(new HashSet<>());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        for(RoleDto roleDto: customerDto.getRoles()){
            RoleDto saveRole=this.roleService.getRole(roleDto.getName());
            customer.addRole(mapper.map(saveRole, Role.class));
        }

        customer=this.userDao.save(customer);

        if(customer==null){
            throw new RuntimeException("bad request");
        }

        return mapper.map(customer,CustomerDto.class);
    }
}
