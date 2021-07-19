package br.com.sgc.controllers;

import br.com.sgc.models.Role;
import br.com.sgc.models.User;
import br.com.sgc.repository.RoleRepository;
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

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        List<Role> roles = roleRepository.findAll();
        user.setRoles(roles);

        User newUser = userRepository.save(user);
        ResponseEntity<User> userResponseEntity = new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);
        return userResponseEntity;
    }

    @RequestMapping(value = "/user/register/check-email-exists/{email}", method = RequestMethod.GET)
    public Boolean create(@PathVariable String email) {
        return userRepository.existsByEmail(email);
    }
}
