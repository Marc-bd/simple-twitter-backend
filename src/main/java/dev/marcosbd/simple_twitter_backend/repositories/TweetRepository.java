package dev.marcosbd.simple_twitter_backend.repositories;

import dev.marcosbd.simple_twitter_backend.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, UUID> {
}
