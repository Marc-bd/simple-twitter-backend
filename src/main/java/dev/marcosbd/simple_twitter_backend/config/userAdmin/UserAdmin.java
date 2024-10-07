package dev.marcosbd.simple_twitter_backend.config.userAdmin;


import dev.marcosbd.simple_twitter_backend.entities.Role;
import dev.marcosbd.simple_twitter_backend.entities.User;
import dev.marcosbd.simple_twitter_backend.repositories.RoleRepository;
import dev.marcosbd.simple_twitter_backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.Set;

@Configuration
public class UserAdmin implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserAdmin(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Role role = roleRepository.findByName(Role.Values.admin.name());

        Optional<User> user = userRepository.findByusername("admin");

        user.ifPresentOrElse(
                userAdmin -> {
                    System.out.println(user);
                },
                () -> {
                    User newUser = new User();
                    newUser.setUsername("admin");
                    newUser.setPassword(passwordEncoder.encode("admin"));
                    newUser.setEmail("admin@mail.com");
                    newUser.setRole(Set.of(role));
                    userRepository.save(newUser);
                }
        );




    }
}
