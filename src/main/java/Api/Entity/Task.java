package Api.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Task")
public class Task {

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

    @ManyToMany(mappedBy = "Tasks")
    private Set<Category> Categories;

    public int getId() {
        return Id;
    }

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project Project;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State State;

    public void setId(int id) {
        Id = id;
    }

    public Set<Category> getCategories() {
        return Categories;
    }

    public void setCategories(Set<Category> categories) {
        Categories = categories;
    }

    public Api.Entity.Project getProject() {
        return Project;
    }

    public void setProject(Api.Entity.Project project) {
        Project = project;
    }

    public Api.Entity.State getState() {
        return State;
    }

    public void setState(Api.Entity.State state) {
        State = state;
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
}
