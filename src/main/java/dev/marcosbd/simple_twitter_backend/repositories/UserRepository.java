package dev.marcosbd.simple_twitter_backend.repositories;

import dev.marcosbd.simple_twitter_backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
