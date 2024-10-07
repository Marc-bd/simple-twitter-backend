package dev.marcosbd.simple_twitter_backend.controller.tokenController;


import dev.marcosbd.simple_twitter_backend.dtos.login.LoginRequest;
import dev.marcosbd.simple_twitter_backend.dtos.login.LoginResponse;
import dev.marcosbd.simple_twitter_backend.entities.User;
import dev.marcosbd.simple_twitter_backend.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

@RestController
public class TokenController {


    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user =  userRepository.findByEmail(loginRequest.getEmail());

        if(user.isEmpty() || !user.get().isCorrectCredentials(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("Invalid email or password");
        }

        var claims = JwtClaimsSet.builder()
                .subject(user.get().getId().toString())
                .expiresAt(Instant.now().plusSeconds(86400))
                .issuedAt(Instant.now())
                .build();


        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, 86400L));




    }
}
