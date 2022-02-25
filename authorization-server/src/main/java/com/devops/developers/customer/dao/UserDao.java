package com.devops.developers.customer.dao;

import com.devops.developers.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Customer, Long> {
    public Customer findCustomerByUsername(String username);
}
