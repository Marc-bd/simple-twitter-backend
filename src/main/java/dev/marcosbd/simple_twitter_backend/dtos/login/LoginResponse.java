package dev.marcosbd.simple_twitter_backend.dtos.login;

public record LoginResponse(String accessToken, Long expiresIn) {
}
