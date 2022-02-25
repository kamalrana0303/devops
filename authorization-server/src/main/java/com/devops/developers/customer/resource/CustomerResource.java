package com.devops.developers.customer.resource;

import com.devops.developers.customer.model.request.CustomerRM;
import com.devops.developers.customer.service.CustomerService;
import com.devops.developers.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("customer")
public class CustomerResource {
    @Autowired
    ModelMapper mapper;
    @Autowired
    CustomerService customerService;

    @PostMapping("sign-up")
    public ResponseEntity<?> singUpCustomer(@RequestBody CustomerRM customerRM){
        CustomerDto customerDto=mapper.map(customerRM, CustomerDto.class);
        customerDto=this.customerService.createCustomer(customerDto);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().build("/sign-up");
        return ResponseEntity.created(uri).build();
    }
}
