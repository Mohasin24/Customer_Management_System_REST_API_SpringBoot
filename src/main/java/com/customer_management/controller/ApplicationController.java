package com.customer_management.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.customer_management.entity.Customer;
import com.customer_management.service.CustomerDao;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class ApplicationController
{
    private CustomerDao customerDao;

    @Autowired
    public ApplicationController(CustomerDao customerDao)
    {
        this.customerDao=customerDao;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //              Get Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/all-customer")
    public List<Customer> getAllCustomers(){
        return customerDao.listAllCustomers();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Customer> retrieveCustomerById(@PathVariable long id){


            Customer customer = customerDao.getCustomerById(id);
            return ResponseEntity.ok(customer);


    }

    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<Customer>> getCustomerByFirstName(@Valid @PathVariable String firstName){

           List<Customer> customer = customerDao.getCustomerByFirstName(firstName);

           return ResponseEntity.ok(customer);

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@Valid @PathVariable String email){

            Customer customer = customerDao.getCustomerByEmail(email);
            return ResponseEntity.ok(customer);


    }

    @GetMapping("/mobile-number/{mobileNo}")
    public ResponseEntity<?> getCustomerByMobileNo(@Valid @PathVariable String mobileNo){

            Customer customer = customerDao.getCustomerByMobileNo(mobileNo);
            return ResponseEntity.ok(customer);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //              Post Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody Customer customer){

            Customer currCustomer = customerDao.createCustomer(customer);
            return ResponseEntity.ok(currCustomer);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //              Delete Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @DeleteMapping("/id/{id}")
    public void removeCustomerById(@PathVariable long id){

            customerDao.removeCustomerById(id);

    }

    @DeleteMapping("/email/{email}")
    public void removeCustomerByEmail(@Valid @PathVariable String email){


            customerDao.removeCustomerByEmail(email);

    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> removeAllCustomer(){

       return customerDao.removeAllCustomer();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //              Put Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {

        System.out.println("Inside the update");

            Customer currCustomer = customerDao.updateCustomer(customer);
            System.out.println(currCustomer);
            System.out.println("Inside the update try");
            return ResponseEntity.ok(currCustomer);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //              End
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/test")
    public String test(){
        throw new RuntimeException("Testing the exception handler");
    }
}
