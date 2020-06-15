package Api.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Task")
public class Task {

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

    @ManyToMany(mappedBy = "tasks", cascade=CascadeType.ALL)
    private Set<Category> categories;

    public int getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    public void setId(int id) {
        this.id = id;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @JsonIgnore
    public Api.Entity.Project getProject() {
        return project;
    }

    public void setProject(Api.Entity.Project project) {
        this.project = project;
    }

    public Api.Entity.State getState() {
        return state;
    }

    public void setState(Api.Entity.State state) {
        this.state = state;
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
}
