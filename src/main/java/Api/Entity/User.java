package Api.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @ManyToMany
    @JoinTable(name = "project_users",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> Projects = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> Roles;

    @JsonIgnore
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public Set<Project> getProjects() {
        return Projects;
    }

    public Set<Role> getRoles() {
        return Roles;
    }

    public void setProjects(Set<Project> projects) {
        Projects = projects;
    }

    public void setRoles(Set<Role> roles) {
        Roles = roles;
    }

    @JsonIgnore
    public void setId(UUID id) {
        this.id = id;
    }

    @JsonIgnore
    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }
}
