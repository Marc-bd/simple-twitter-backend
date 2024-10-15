package dev.marcosbd.simple_twitter_backend.dtos.tweet;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateTweetDTO {

    @NotBlank(message = "required field")
    private String content;

}
