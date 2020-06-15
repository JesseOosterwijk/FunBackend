package Api.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Project")
@EntityListeners(AuditingEntityListener.class)
public class Project {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name")
    @Size(min = 2)
    private String name;

    @Column(name = "Description")
    @Size(min = 10)
    private String description;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<>();

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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void addUser(User user) {
        users.add(user);
        user.getProjects().add(this);
    }

    public void deleteUser(User user) {
        users.remove(user);
        user.getProjects().remove(this);
    }

    public void addCategory(Category category) {
        categories.add(category);
        category.setProject(this);
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setProject(this);
    }

    public void addRole(Role role) {
        roles.add(role);
        role.setProject(this);
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
