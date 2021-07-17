package br.com.sgc.config;

import br.com.sgc.domain.Const;
import br.com.sgc.models.Role;
import br.com.sgc.models.User;
import br.com.sgc.repository.RoleRepository;
import br.com.sgc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            createUser("Admin", "admin", encoder.encode("123456"), Const.ROLE_ADMIN);
            createUser("Cliente", "cliente", encoder.encode("123456"), Const.ROLE_CLIENT);
        }
    }

    public void createUser(String name, String email, String password, String roleName) {

        Role role = new Role(roleName);

        this.roleRepository.save(role);
        User user = new User(name, email, password, Arrays.asList(role));
        userRepository.save(user);
    }
}