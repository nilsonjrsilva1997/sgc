package br.com.sgc.controllers;

import br.com.sgc.models.*;
import br.com.sgc.repository.CustomerRepository;
import br.com.sgc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/customers")
    public List<Customer> customerList() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer customerById(@PathVariable(value = "id") long id) {
        return customerRepository.findById(id);
    }

    @RequestMapping(value = "/customer/create", method = RequestMethod.POST)
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        setData(customer);
        User currentUser = userRepository.findFirstByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        customer.setUser(currentUser);
        Customer newCustomer = customerRepository.save(customer);
        ResponseEntity<Customer> userResponseEntity = new ResponseEntity<>(newCustomer, HttpStatus.ACCEPTED);
        return userResponseEntity;
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Customer customer) {
        try {
            Customer customerRecord = customerRepository.findById(id);
            customerRecord.setCpfCnpj(customer.getCpfCnpj());
            customerRecord.setEmail(customer.getEmail());
            customerRecord.setName(customer.getName());

            setData(customer);

            return new ResponseEntity(customerRepository.save(customerRecord), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    private void setData(@RequestBody Customer customer) {
        List<Address> addressList = customer.getAddresses();
        addressList.forEach(address -> {
            address.setCustomer(customer);
        });

        List<Phone> phoneList = customer.getPhones();
        phoneList.forEach(phone -> {
            phone.setCustomer(customer);
        });
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity delete(@PathVariable("id") long id, @RequestBody Customer customer) {
        try {
            return new ResponseEntity(customerRepository.deleteById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("customer/exists/{cpfCnpj}")
    public Boolean existsCpfCnpj(@PathVariable String cpfCnpj) {
        return customerRepository.existsByCpfCnpj(cpfCnpj);
    }
}
