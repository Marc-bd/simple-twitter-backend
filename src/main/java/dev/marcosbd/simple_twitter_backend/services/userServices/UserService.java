package dev.marcosbd.simple_twitter_backend.services.userServices;


import dev.marcosbd.simple_twitter_backend.dtos.user.CreateUserDTO;
import dev.marcosbd.simple_twitter_backend.entities.Role;
import dev.marcosbd.simple_twitter_backend.entities.User;
import dev.marcosbd.simple_twitter_backend.repositories.RoleRepository;
import dev.marcosbd.simple_twitter_backend.repositories.UserRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

//    public User create(CreateUserDTO userData) {
//        Role userRole = roleRepository.findByName(Role.Values.user.name());
//
//        Optional<User> userExist = userRepository.findByEmail(userData.email());
//
//        if (userExist.isPresent()) {
//            throw new DuplicateKeyException("User Already Exists");
//        }
//
//
//
//
//    }


}
