package Api.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Role")
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "Name")
    @Size(min = 2)
    private String name;

    @NotBlank
    @Column(name = "Description")
    @Size(min = 10)
    private String description;

    @ManyToMany(mappedBy = "roles", cascade=CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void addUser(User user) {
        users.add(user);
        user.getRoles().add(this);
    }
}
