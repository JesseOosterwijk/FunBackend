package Api.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotBlank
    @Column(name = "Name")
    private String name;

    @NotBlank
    @Column(name = "Email")
    private String email;

    @JsonIgnore
    @NotBlank
    @Column(name = "Password")
    private String password;

    @ManyToMany
    @JoinTable(name = "ProjectUsers",
            joinColumns = {@JoinColumn(name = "UserId")},
            inverseJoinColumns = {@JoinColumn(name = "ProjectId")})
    private Set<Project> Projects = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "UserRoles",
            joinColumns = {@JoinColumn(name = "UserId")},
            inverseJoinColumns = {@JoinColumn(name = "RoleId")})
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
