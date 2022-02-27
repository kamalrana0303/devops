package com.devops.developers.customer.dao;

import com.devops.developers.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<Customer, Long> {
    public Optional<Customer> findCustomerByUsername(String username);
}
