package fr.lernejo.search.api;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

import static fr.lernejo.search.api.AmqpConfiguration.GAME_INFO_QUEUE;

@Component
public class GameInfoListener {
    private final RestHighLevelClient restHighLevelClient;

    public GameInfoListener(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @RabbitListener(queues = GAME_INFO_QUEUE)
    public void onMessage(String message, @Header("game_id") String id) throws IOException {
        IndexRequest indexRequest = new IndexRequest("games");
        indexRequest.source(message, XContentType.JSON);
        indexRequest.id(id);
        this.restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }
}
