package se.sti.email_microservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.sti.email_microservice.config.RabbitConfig;
import se.sti.email_microservice.service.EmailService;

@Component
public class EmailConsumer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final EmailService emailService;

    @Value("${app.mail.receiver}")
    private String ownerEmail;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleMessage(String message) {
        log.info("Received message: {}", message);

        if (message.startsWith("CREATED:")) {
            emailService.sendEmail(
                    ownerEmail,
                    "New User Registered",
                    "A new user has been registered in the system."
            );

        } else if (message.startsWith("DELETED:")) {
            emailService.sendEmail(
                    ownerEmail,
                    "User Deleted",
                    "A user has been removed from the system."
            );
        }
    }
}
