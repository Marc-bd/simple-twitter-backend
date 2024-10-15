package dev.marcosbd.simple_twitter_backend.dtos.feed;

import java.util.UUID;

public record FeedItemDTO (UUID tweetId, String Content, String username) {
}
