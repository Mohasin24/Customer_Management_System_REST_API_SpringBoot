package com.customer_management.dao;

import com.customer_management.entity.Customer;
import com.customer_management.repository.CustomerRepo;
import com.customer_management.service.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerDaoImpl implements CustomerDao {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //          Fields
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private CustomerRepo customerRepo;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //          Constructors
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    public CustomerDaoImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                  Read methods
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<Customer> listAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(long id) throws Exception {

        Optional<Customer> customer = customerRepo.findById(id);

        return customer.orElseThrow(
                () -> {
                    throw new NoSuchElementException("Customer not found with id " + id);
                }
        );
    }

    @Override
    public List<Customer> getCustomerByFirstName(String firstName) throws Exception {


        Optional<List<Customer>> customer = customerRepo.findCustomerByFirstName(firstName);

        customer.orElseThrow(
                () -> {
                    throw new RuntimeException("Customer not found with first-name " + firstName);
                }
        );

        return customer.get();
    }

    @Override
    public Customer getCustomerByEmail(String email) throws Exception {
        Optional<Customer> customer = customerRepo.findCustomerByEmail(email);

        customer.orElseThrow(
                () -> {
                    throw new RuntimeException("Customer not found with email " + email);
                }
        );

        return customer.get();
    }

    @Override
    public Customer getCustomerByMobileNo(String mobileNo) throws Exception {
        Optional<Customer> customer = customerRepo.findByMobileNo(mobileNo);

        customer.orElseThrow(()->{
            throw new RuntimeException("Customer not found with mobile number " + mobileNo);
        });

        return  customer.get();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                      Create Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Customer createCustomer(Customer customer) {

        Optional<Customer> customerByEmail = customerRepo.findCustomerByEmail(customer.getEmail());

        Optional<Customer> customerByMobile = customerRepo.findByMobileNo(customer.getMobileNo());

        if (customerByEmail.isPresent()) {
            throw new RuntimeException(customer.getEmail() + " Email already exist");

        } else if (customerByMobile.isPresent()) {
            throw new RuntimeException(customer.getMobileNo() + " Mobile number already exist");
        }

        return customerRepo.save(customer);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                      Update Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Customer updateCustomer(Customer customer) throws Exception {
        Optional<Customer> currentCustomer = customerRepo.findById(customer.getId());

        currentCustomer.orElseThrow(
                () -> {
                    throw new RuntimeException("Customer not found with id " + customer.getId());
                }
        );

        return customerRepo.save(customer);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //              Delete Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ResponseEntity<?> removeCustomerById(long id) throws Exception {

        Optional<Customer> customer = customerRepo.findById(id);

        customer.orElseThrow(
                () -> {
                    throw new NoSuchElementException("Customer not found with id " + id);
                }
        );

        customerRepo.deleteById(id);

        return new ResponseEntity<String>(("Customer record with id " + id + " successfully deleted."), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> removeCustomerByEmail(String email) throws Exception {
        Optional<Customer> customer = customerRepo.findCustomerByEmail(email);

        customer.orElseThrow(
                () -> {
                    throw new RuntimeException("Customer not found with email " + email);
                }
        );

        customerRepo.delete(customer.get());

        return new ResponseEntity<String>(("Customer record with email " + email + " successfully deleted."), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> removeAllCustomer() {

        customerRepo.deleteAll();
        return new ResponseEntity<String>("All customer records deleted successfully.", HttpStatus.OK);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //                  End
    ///////////////////////////////////////////////////////////////////////////////////////////////
}
