package Api.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "Task")
public class Task {

    @Id
    private int Id;

    @NotBlank
    private String Name;

    @NotBlank
    private String Description;

    @ManyToMany
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
