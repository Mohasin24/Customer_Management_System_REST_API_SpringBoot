package com.customer_management.service;




import org.springframework.http.ResponseEntity;

import com.customer_management.entity.Customer;

import java.util.List;

public interface CustomerDao
{
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                  Read methods
    ///////////////////////////////////////////////////////////////////////////////////////////////
    List<Customer> listAllCustomers();
    Customer getCustomerById(long id);
    List<Customer> getCustomerByFirstName(String firstName);
    Customer getCustomerByEmail(String email);

    Customer getCustomerByMobileNo(String mobileNo);

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                      Create Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////
    Customer createCustomer(Customer customer);

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                      Update Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////

    Customer updateCustomer(Customer customer);

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //              Delete Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ResponseEntity<?> removeCustomerById(long id);
    ResponseEntity<?> removeCustomerByEmail(String email);
    ResponseEntity<?> removeAllCustomer();
}

