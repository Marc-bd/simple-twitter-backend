package dev.marcosbd.simple_twitter_backend.dtos.login;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "required field")
    private String email;
    @NotBlank(message = "required field")
    private String password;


}
