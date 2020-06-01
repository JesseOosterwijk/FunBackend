package Api.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Role")
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @Column(name = "Id")
    private int Id;

    @NotBlank
    @Column(name = "Name")
    @Size(min = 2)
    private String Name;

    @NotBlank
    @Column(name = "Description")
    @Size(min = 10)
    private String Description;

    @ManyToMany(mappedBy = "Roles")
    private Set<User> users;
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

    public void setId(int id) {
        Id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void addUser(User user) {
        users.add(user);
        user.getRoles().add(this);
    }
}
