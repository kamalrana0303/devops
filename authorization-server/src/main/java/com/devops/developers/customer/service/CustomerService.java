package com.devops.developers.customer.service;

import com.devops.developers.dto.CustomerDto;

public interface CustomerService {
    public CustomerDto createCustomer(CustomerDto customerDto);
    public CustomerDto getCustomerByName(String name);
}
