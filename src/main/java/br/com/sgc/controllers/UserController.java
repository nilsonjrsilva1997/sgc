package br.com.sgc.controllers;

import br.com.sgc.models.Address;
import br.com.sgc.models.Phone;
import br.com.sgc.models.User;
import br.com.sgc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        List<Address> addressList = user.getAddresses();
        addressList.forEach(address -> {
            address.setUser(user);
        });

        List<Phone> phoneList = user.getPhones();
        phoneList.forEach(phone -> {
            phone.setUser(user);
        });

        User newUser = userRepository.save(user);

        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);

        return userResponseEntity;
    }
}
