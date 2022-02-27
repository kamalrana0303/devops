package com.devops.developers.config;

import com.devops.developers.ClientAttributes;
import com.devops.developers.ClientConfiguration;
import com.devops.developers.client.model.request.CAuthoritiesRM;
import com.devops.developers.client.model.request.ClientDetailsRM;
import com.devops.developers.client.model.request.GrantTypeRM;
import com.devops.developers.client.model.request.ScopeRM;
import com.devops.developers.client.service.impl.ClientDetailServiceImpl;
import com.devops.developers.customer.entity.Permission;
import com.devops.developers.customer.entity.PermissionName;
import com.devops.developers.customer.entity.RoleName;
import com.devops.developers.customer.model.request.CustomerRM;
import com.devops.developers.customer.service.CustomerService;
import com.devops.developers.customer.service.PermissionService;
import com.devops.developers.customer.service.RoleService;
import com.devops.developers.dto.CustomerDto;
import com.devops.developers.dto.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Configuration
public class CommandLine {


    @Autowired
    CustomerService customerService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    @Autowired
    ClientConfiguration clientConfiguration;
    @Autowired
    ClientDetailServiceImpl clientDetailService;

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                RoleName[] roles = RoleName.values();
//                for(RoleName role: roles){
//                    roleService.createRole(role);
//                }

//                CustomerDto isExistAlready=customerService.getCustomerByName("system");
//                if(isExistAlready==null){
//                    CustomerDto customerDto= new CustomerDto();
//                    customerDto.setEmail("system@system.in");
//                    customerDto.setUsername("system");
//                    customerDto.setPassword("system");
//                    customerDto.setPhoneNumber("8376857964");
//                    customerDto.setRoles(Collections.singleton(new RoleDto(RoleName.ADMIN)));
//                    customerService.createCustomer(customerDto);
//                }

               for(Map.Entry<String, ClientAttributes> cl: clientConfiguration.getClients().entrySet()){
                   String clientName= cl.getKey();
                   ClientDetailsRM clientDetailsRM= new ClientDetailsRM();
                   clientDetailsRM.setClientId(clientName);

                   clientDetailsRM.setClientSecret(cl.getValue().getClientSecret());
                   clientDetailsRM.setcAuthorities(Arrays.asList(cl.getValue().getAuthorities()).stream().map(x->new CAuthoritiesRM(x)).collect(Collectors.toSet()));
                   clientDetailsRM.setScopes(Arrays.asList(cl.getValue().getScopes()).stream().map(x->new ScopeRM(x)).collect(Collectors.toSet()));
                   clientDetailsRM.setAuthorizedGrantTypes(Arrays.asList(cl.getValue().getGrantTypes()).stream().map(x->new GrantTypeRM(x)).collect(Collectors.toSet()));
                   ClientDetails clientDetails=clientDetailService.clientRegistertaion(clientDetailsRM);
               }
            }
        };
    }
}

