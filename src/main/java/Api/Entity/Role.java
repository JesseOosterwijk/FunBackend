package Api.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "Role")
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    private int Id;

    @NotBlank
    private String Name;

    @NotBlank
    private String Description;

    @ManyToMany(mappedBy = "Roles")
    private Set<User> Users;
    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void addUser(User user) {
        Users.add(user);
        user.getRoles().add(this);
    }
}
