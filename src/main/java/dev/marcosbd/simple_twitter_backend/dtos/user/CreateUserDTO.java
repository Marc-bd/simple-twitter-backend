package dev.marcosbd.simple_twitter_backend.dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserDTO {

    @NotBlank(message = "required field")
    private String username;
    @NotBlank(message = "required field")
    private String password;
    @NotBlank(message = "required field")
    private String email;
}
