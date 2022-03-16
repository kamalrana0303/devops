package com.devops.developers.customer.resource;

import com.devops.developers.customer.entity.RoleName;
import com.devops.developers.customer.model.request.CustomerRM;
import com.devops.developers.customer.model.response.CustomerRest;
import com.devops.developers.customer.service.CustomerService;
import com.devops.developers.dto.CustomerDto;
import com.devops.developers.dto.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("customer")
public class CustomerResource {

    private final ModelMapper mapper;
    private final CustomerService customerService;

    public CustomerResource(ModelMapper mapper, CustomerService customerService) {
        this.mapper = mapper;
        this.customerService = customerService;
    }



//    @PostMapping("login")
//    public ResponseEntity<?> userLogin(@RequestBody CustomerRM customerRM){
//
//    }
//
//    @PostMapping("sign-up")
//    public ResponseEntity<?> singUpCustomer(@RequestBody CustomerRM customerRM) {
//        CustomerDto customerDto = mapper.map(customerRM, CustomerDto.class);
//        customerDto.setRoles(Collections.singleton(new RoleDto(RoleName.USER)));
//        customerDto = this.customerService.createCustomer(customerDto);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build("/sign-up");
//        return ResponseEntity.ok(mapper.map(customerDto, CustomerRest.class));
//    }

    @GetMapping("{username}")
    public ResponseEntity<?> getCustomerByName(@PathVariable("username") String username, Authentication a) {
        CustomerDto customerByName = this.customerService.getCustomerByName(username);
        return ResponseEntity.ok(mapper.map(customerByName, CustomerRest.class));
    }

}
