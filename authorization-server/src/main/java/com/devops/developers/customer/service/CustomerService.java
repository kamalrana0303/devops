package com.devops.developers.customer.service;

import com.devops.developers.dto.CustomerDto;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerByName(String name);
}
