package com.customer_management.repository;

import com.customer_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<List<Customer>> findCustomerByFirstName(String firstName);
    Optional<Customer> findCustomerByEmail(String firstName);
    Optional<Customer> findByMobileNo(String mobileNo);
}
