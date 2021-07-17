package br.com.sgc.controllers;

import br.com.sgc.domain.Const;
import br.com.sgc.models.User;
import br.com.sgc.repository.AddressRepository;
import br.com.sgc.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    AddressRepository addressRepository;

    @RequestMapping(value = "/user-auth", method = RequestMethod.GET)
    @ResponseBody
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    public User user() {
        User user = ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());

        user.setPhones(phoneRepository.findAllByUserId(user.getId()));
        user.setAddresses(addressRepository.findAllByUserId(user.getId()));

        return user;
    }
}
