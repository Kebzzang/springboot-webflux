package com.keb.fluxdemo.web;

import com.keb.fluxdemo.domain.Customer;
import com.keb.fluxdemo.domain.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @GetMapping("/customer")
    public Flux<Customer> findAll(){
        return customerRepository.findAll().log();
    }

}
