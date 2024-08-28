package com.assessment.cms.controller;

import com.assessment.cms.entity.Customer;
import com.assessment.cms.entity.PhoneDTO;
import com.assessment.cms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try{
            return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        try {
            return new ResponseEntity<>(customerService.getCustomer(customerId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerId, @RequestBody PhoneDTO phoneDTO) {
        try {
            String phoneNo = phoneDTO.getPhoneNo();
            return new ResponseEntity<>(customerService.updateCustomer(customerId, phoneNo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
