package se.sti.email_microservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "email-queue";

    @Bean
    public Queue userQueue() {
        return new Queue(QUEUE_NAME, true); // true = durable
    }
}



