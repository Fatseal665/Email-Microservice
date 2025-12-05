package se.sti.email_microservice.config;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${email.queue}")
    private String queueName;

    @Bean
    public Queue userEventQueue() {
        return new Queue(queueName, true);
    }
}
