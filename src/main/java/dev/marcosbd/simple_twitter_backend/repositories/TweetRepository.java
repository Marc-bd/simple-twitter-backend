package dev.marcosbd.simple_twitter_backend.repositories;

import dev.marcosbd.simple_twitter_backend.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
