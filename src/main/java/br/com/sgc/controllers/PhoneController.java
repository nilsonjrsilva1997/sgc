package br.com.sgc.controllers;

import br.com.sgc.models.Phone;
import br.com.sgc.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneController {
    @Autowired
    PhoneRepository phoneRepository;

    @GetMapping("/phones")
    public List<Phone> phoneList() {
        return (List<Phone>) phoneRepository.findAll();
    }

    @GetMapping("/phone/{id}")
    public Phone customerById(@PathVariable(value = "id") long id) {
        return phoneRepository.findById(id);
    }

    @PostMapping("/user/register/phone")
    public List<Phone> create(@RequestBody List<Phone> phones) {
        return phoneRepository.saveAll(phones);
    }

    @PutMapping("/phone/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Phone phone) {
        try {
            Phone phoneRecord = phoneRepository.findById(id);
            phoneRecord.setNumber(phone.getNumber());
            return new ResponseEntity(phoneRepository.save(phoneRecord), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
