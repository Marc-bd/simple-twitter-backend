package dev.marcosbd.simple_twitter_backend.dtos.feed;

import java.util.List;

public record FeedRequestDTO (
        List<FeedItemDTO> feedItens,
        int page,
        int pageSize,
        int totalPages,
        Long total
){

}
