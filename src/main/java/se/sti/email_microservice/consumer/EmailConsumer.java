package se.sti.email_microservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import se.sti.email_microservice.config.RabbitConfig;
import se.sti.email_microservice.service.EmailService;

@Component
public class EmailConsumer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleMessage(String message) {
        log.info("Received message: {}", message);

        if (message.startsWith("CREATED:")) {
            String email = message.split(":")[1];
            emailService.sendEmail(email, "Welcome!", "You have been successfully registered!");
        } else if (message.startsWith("DELETED:")) {
            String email = message.split(":")[1];
            emailService.sendEmail(email, "Account Deleted", "Your account has been deleted.");
        }
    }
}


