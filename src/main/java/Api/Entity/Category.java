package Api.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Category")
@EntityListeners(AuditingEntityListener.class)
public class Category {

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

    @ManyToMany
    @JoinTable(name = "category_tasks",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "tasks_id")})
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

    public void setId(int id) {
        Id = id;
    }

    public Set<Task> getTasks() {
        return Tasks;
    }

    public void setTasks(Set<Task> tasks) {
        Tasks = tasks;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
