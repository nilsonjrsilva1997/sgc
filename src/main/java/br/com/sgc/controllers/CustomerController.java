package br.com.sgc.controllers;

import br.com.sgc.models.Customer;
import br.com.sgc.repository.CustomerReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")

public class CustomerController {
    @Autowired
    CustomerReposity customerReposity;

    @GetMapping("/customers")
    public List<Customer> customerList() {
        return customerReposity.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer customerById(@PathVariable(value = "id") long id) {
        return customerReposity.findById(id);
    }

    @PostMapping("/customer")
    public Customer create(@RequestBody Customer customer) {
        return customerReposity.save(customer);
    }
}
