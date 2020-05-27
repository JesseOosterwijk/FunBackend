package Api.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "Project")
@EntityListeners(AuditingEntityListener.class)
public class Project {

    @Id
    private int Id;

    @NotBlank
    private String Name;

    @NotBlank
    private String Description;

    @ManyToMany(mappedBy = "Projects")
    private Set<User> users;

    @OneToMany(mappedBy = "Project")
    private Set<Task> Tasks;

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

    public void setId(int id) {
        Id = id;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setTasks(Set<Task> tasks) {
        Tasks = tasks;
    }

    public void addUser(User user) {
        users.add(user);
        user.getProjects().add(this);
    }
}
