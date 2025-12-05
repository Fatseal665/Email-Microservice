package se.sti.email_microservice.model;

public class UserEvent {
    private String email;
    private String username;
    private String type; // "CREATED" eller "DELETED"

    public UserEvent() {}

    public UserEvent(String email, String username, String type) {
        this.email = email;
        this.username = username;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
