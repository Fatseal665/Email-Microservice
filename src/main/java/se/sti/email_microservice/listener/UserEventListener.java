package se.sti.email_microservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import se.sti.email_microservice.model.UserEvent;
import se.sti.email_microservice.service.EmailService;
import tools.jackson.databind.ObjectMapper;

@Service
public class UserEventListener {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public UserEventListener(EmailService emailService, ObjectMapper objectMapper) {
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "${email.queue}")
    public void handleUserEvent(String message) throws Exception {
        UserEvent event = objectMapper.readValue(message, UserEvent.class);

        if ("CREATED".equals(event.getType())) {
            emailService.sendEmail(
                    event.getEmail(),
                    "Welcome " + event.getUsername(),
                    "You have been successfully registered!"
            );
        } else if ("DELETED".equals(event.getType())) {
            emailService.sendEmail(
                    event.getEmail(),
                    "Deleted account" + event.getUsername(),
                    "Your account has been deleted."
            );
        }
    }
}
