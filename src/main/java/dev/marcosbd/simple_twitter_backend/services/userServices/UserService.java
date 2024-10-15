package dev.marcosbd.simple_twitter_backend.services.userServices;


import dev.marcosbd.simple_twitter_backend.dtos.user.CreateUserDTO;
import dev.marcosbd.simple_twitter_backend.dtos.user.ResponseCreateUser;
import dev.marcosbd.simple_twitter_backend.entities.Role;
import dev.marcosbd.simple_twitter_backend.entities.User;
import dev.marcosbd.simple_twitter_backend.repositories.RoleRepository;
import dev.marcosbd.simple_twitter_backend.repositories.UserRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseCreateUser create(CreateUserDTO userData) {
        Role userRole = roleRepository.findByName(Role.Values.user.name());

        Optional<User> userExist = userRepository.findByEmail(userData.getEmail());

        System.out.println("service : " + userData);

        if (userExist.isPresent()) {
            throw new DuplicateKeyException("User Already Exists");
        }

        User newUser = new User(userData, passwordEncoder);

        newUser.setRole(Set.of(userRole));


        userRepository.save(newUser);

        return new ResponseCreateUser(newUser);
    }

    public List<ResponseCreateUser> getUsers() {
        List<User> users = userRepository.findAll();
        List<ResponseCreateUser> responseCreateUsers = new ArrayList<>();

        for (User user : users) {
            responseCreateUsers.add(new ResponseCreateUser(user));
        }

        return responseCreateUsers;
    }

}
