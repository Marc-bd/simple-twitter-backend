package dev.marcosbd.simple_twitter_backend.controller.userController;


import dev.marcosbd.simple_twitter_backend.dtos.user.CreateUserDTO;
import dev.marcosbd.simple_twitter_backend.dtos.user.ResponseCreateUser;
import dev.marcosbd.simple_twitter_backend.entities.Role;
import dev.marcosbd.simple_twitter_backend.entities.User;
import dev.marcosbd.simple_twitter_backend.repositories.RoleRepository;
import dev.marcosbd.simple_twitter_backend.repositories.UserRepository;
import dev.marcosbd.simple_twitter_backend.services.userServices.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/auth/register")
    public ResponseEntity<ResponseCreateUser> createUser(@RequestBody
                                           CreateUserDTO userData) {


        ResponseCreateUser newUser = userService.create(userData);

        return ResponseEntity.ok().body(newUser);
    }



    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<List<ResponseCreateUser>> getUsers() {
        List<ResponseCreateUser> users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }


}
