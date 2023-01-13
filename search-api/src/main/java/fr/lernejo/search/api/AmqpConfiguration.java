package fr.lernejo.search.api;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
    static final String GAME_INFO_QUEUE = "game_info";

    @Bean
    Queue queue() {
        return new Queue(GAME_INFO_QUEUE, true);
    }
}
