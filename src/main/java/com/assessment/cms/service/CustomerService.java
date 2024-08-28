package com.assessment.cms.service;

import com.assessment.cms.entity.Customer;
import com.assessment.cms.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> updateCustomer(Long customerId, String phoneNo) {
        if(customerRepository.existsById(customerId)) {
            Customer existingCustomer = customerRepository.findById(customerId).orElse(null);
            if(existingCustomer != null) {
                existingCustomer.setPhoneNo(phoneNo);
                customerRepository.save(existingCustomer);
                return Optional.of(existingCustomer);
            }
        }
        return Optional.empty();
    }


    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

}
