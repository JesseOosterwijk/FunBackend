package Api.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "User")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;

    @NotBlank
    private String Name;

    @NotBlank
    private String Email;

    @JsonIgnore
    @NotBlank
    private String Password;

    @JsonIgnore
    public UUID getId() {
        return Id;
    }

    @JsonIgnore
    public String getName() {
        return Name;
    }

    @JsonIgnore
    public String getEmail() {
        return Email;
    }

    @JsonIgnore
    public String getPassword() {
        return Password;
    }

    @JsonIgnore
    public void setName(String name) {
        Name = name;
    }

    @JsonIgnore
    public void setEmail(String email) {
        this.Email = email;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.Password = password;
    }
}
