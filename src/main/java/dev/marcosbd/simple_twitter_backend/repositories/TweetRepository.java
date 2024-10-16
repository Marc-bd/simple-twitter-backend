package dev.marcosbd.simple_twitter_backend.repositories;

import dev.marcosbd.simple_twitter_backend.entities.Tweet;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, UUID> {

    List<Tweet> findByUser_Id(UUID id);

}