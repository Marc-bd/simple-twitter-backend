package dev.marcosbd.simple_twitter_backend.services.tweetServices;

import dev.marcosbd.simple_twitter_backend.dtos.tweet.CreateTweetDTO;
import dev.marcosbd.simple_twitter_backend.entities.Tweet;
import dev.marcosbd.simple_twitter_backend.entities.User;
import dev.marcosbd.simple_twitter_backend.repositories.TweetRepository;
import dev.marcosbd.simple_twitter_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TweetService {

    private TweetRepository tweetRepository;
    private UserRepository userRepository;


    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }


    public Tweet createTweet(UUID userId, CreateTweetDTO tweet) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");

        }
        Tweet newTweet = new Tweet();
        newTweet.setUser(user.get());
        newTweet.setContent(tweet.getContent());
        tweetRepository.save(newTweet);
        return newTweet;
    }

    public List<Tweet> getAllTweets() {
        List<Tweet> tweets = tweetRepository.findAll();
        return tweets;
    }

    public List<Tweet> getTweetsByUserId(UUID userId) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");

        }

        List<Tweet> tweetList = tweetRepository.findByUser_Id(userId);
        return tweetList;
    }

    public void DeleteTweet(UUID tweetId, UUID userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Optional<Tweet> tweet = tweetRepository.findById(tweetId);

        if (tweet.isEmpty()) {
            throw new RuntimeException("Tweet not found");
        }

        if(!tweet.get().getUser().getId().equals(userId)) {
            throw new RuntimeException("This tweet does not belong to the user.");
        }

        tweetRepository.delete(tweet.get());

    }

}
