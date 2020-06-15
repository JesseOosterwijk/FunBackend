package Api.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "Id")
    private UUID id;


    @Column(name = "Name")
    @Size(min = 3, message = "Name not long enough")
    private String name;


    @Column(name = "Email")
    @Size(min = 4, message = "email not valid")
    @Email
    private String email;

    @JsonIgnore
    @Column(name = "Password")
    @Size(min = 5, message = "password not long enough")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "project_users",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public Set<Project> getProjects() {
        return projects;
    }

    @JsonIgnore
    public Set<Role> getRoles() {
        return roles;
    }

    @JsonIgnore
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @JsonIgnore
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
