package dev.marcosbd.simple_twitter_backend.controller.userController;


import dev.marcosbd.simple_twitter_backend.dtos.user.CreateUserDTO;
import dev.marcosbd.simple_twitter_backend.entities.Role;
import dev.marcosbd.simple_twitter_backend.entities.User;
import dev.marcosbd.simple_twitter_backend.repositories.RoleRepository;
import dev.marcosbd.simple_twitter_backend.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


//    @PostMapping("/users")
//    public ResponseEntity<User> createUser(@RequestBody
//                                           CreateUserDTO userData) {
//
//        Role userRole = roleRepository.findByName(Role.Values.user.name());
//
//
//
//        return ResponseEntity.ok()
//
//    }

}
