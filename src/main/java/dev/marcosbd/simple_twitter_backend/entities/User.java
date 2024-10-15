package dev.marcosbd.simple_twitter_backend.entities;

import dev.marcosbd.simple_twitter_backend.dtos.login.LoginRequest;
import dev.marcosbd.simple_twitter_backend.dtos.user.CreateUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonIgnore
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    @JsonIgnore
    private Set<Role> role;

    public User(CreateUserDTO userData, PasswordEncoder passwordEncoder) {
        this.username = userData.getUsername();
        this.password = passwordEncoder.encode(userData.getPassword());
        this.email = userData.getEmail();
    }


    public boolean isCorrectCredentials(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.getPassword(), this.password);
    }

}
