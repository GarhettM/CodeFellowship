package garhett.codefellowship.models.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class MessagePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    public ApplicationUser applicationUser;

    String message;
    String username;
    Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

    public MessagePost() {};

    public MessagePost(String message, String username) {
        this.message = message;
        this.username = username;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "MessagePost{" +
                "id=" + id +
                ", applicationUser=" + applicationUser +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
