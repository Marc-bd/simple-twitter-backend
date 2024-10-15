package dev.marcosbd.simple_twitter_backend.controller.tweetController;


import dev.marcosbd.simple_twitter_backend.dtos.tweet.CreateTweetDTO;
import dev.marcosbd.simple_twitter_backend.entities.Tweet;
import dev.marcosbd.simple_twitter_backend.services.tweetServices.TweetService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TweetController {

    private TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping("/tweets")
    public ResponseEntity<Tweet> createTweet(
            @RequestBody
            CreateTweetDTO tweet, JwtAuthenticationToken token) {

        UUID userUUID = UUID.fromString(token.getName());

        Tweet newTweet = tweetService.createTweet(userUUID, tweet);
        return ResponseEntity.ok().body(newTweet);

    }

    @GetMapping("/tweets")
    public ResponseEntity<List<Tweet>> getAllTweets() {
        List<Tweet> tweets = tweetService.getAllTweets();
        return ResponseEntity.ok().body(tweets);
    }

    @GetMapping("/mytweets")
    public ResponseEntity<List<Tweet>> getMyTweets(JwtAuthenticationToken token) {
        UUID userUUID = UUID.fromString(token.getName());
        List<Tweet> tweets = tweetService.getTweetsByUserId(userUUID);
        return ResponseEntity.ok().body(tweets);
    }


    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweet(
            @PathVariable("id")
            String tweetID, JwtAuthenticationToken token) {
        UUID tweetId = UUID.fromString(tweetID);
        UUID userId = UUID.fromString(token.getName());

        tweetService.DeleteTweet(tweetId, userId);

        return ResponseEntity.noContent().build();


    }


}
