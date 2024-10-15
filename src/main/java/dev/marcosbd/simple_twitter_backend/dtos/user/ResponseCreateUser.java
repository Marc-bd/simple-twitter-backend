package dev.marcosbd.simple_twitter_backend.dtos.user;

import dev.marcosbd.simple_twitter_backend.entities.User;

import java.util.UUID;

public record ResponseCreateUser(UUID id, String username, String email) {
    // Construtor que recebe o objeto User e inicializa o record
    public ResponseCreateUser(User user) {
        this(user.getId(), user.getUsername(), user.getEmail());
    }
}
