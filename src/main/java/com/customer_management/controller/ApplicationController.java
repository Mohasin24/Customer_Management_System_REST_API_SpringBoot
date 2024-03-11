package com.customer_management.controller;

import com.customer_management.entity.Customer;
import com.customer_management.service.CustomerDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
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

        try{
            Customer customer = customerDao.getCustomerById(id);
            return ResponseEntity.ok(customer);
        }catch (Exception obj){
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

    }

    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<Customer>> getCustomerByFirstName(@Valid @PathVariable String firstName){
       try {
           List<Customer> customer = customerDao.getCustomerByFirstName(firstName);
           return ResponseEntity.ok(customer);
       }catch (Exception obj){
           System.out.println(obj.getMessage());
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@Valid @PathVariable String email){
        try{
            Customer customer = customerDao.getCustomerByEmail(email);
            return ResponseEntity.ok(customer);
        }
        catch (Exception obj){
            return new ResponseEntity<String>(obj.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mobile-number/{mobileNo}")
    public ResponseEntity<?> getCustomerByMobileNo(@Valid @PathVariable String mobileNo){
        try{
            Customer customer = customerDao.getCustomerByMobileNo(mobileNo);
            return ResponseEntity.ok(customer);
        }
        catch (Exception obj){
            return new ResponseEntity<String>(obj.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //              Post Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody Customer customer){
        try{
            Customer currCustomer = customerDao.createCustomer(customer);
            return ResponseEntity.ok(currCustomer);
        }catch (Exception obj){

            return new ResponseEntity<String>(obj.getMessage(), HttpStatus.CONFLICT);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //              Delete Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @DeleteMapping("/id/{id}")
    public void removeCustomerById(@PathVariable long id){
        try{
            customerDao.removeCustomerById(id);
        }
        catch (Exception obj){
            System.out.println(obj.getMessage());
        }
    }

    @DeleteMapping("/email/{email}")
    public void removeCustomerByEmail(@Valid @PathVariable String email){

        try {
            customerDao.removeCustomerByEmail(email);
        }
        catch (Exception obj){
            System.out.println(obj.getMessage());
        }
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
        try{
            Customer currCustomer = customerDao.updateCustomer(customer);
            System.out.println(currCustomer);
            System.out.println("Inside the update try");
            return ResponseEntity.ok(currCustomer);
        }catch (Exception obj){

            System.out.println(obj.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //              End
    ///////////////////////////////////////////////////////////////////////////////////////////////
}
