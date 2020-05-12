package Api.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "Category")
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    private int Id;

    @NotBlank
    private String Name;

    @NotBlank
    private String Description;

    @ManyToMany
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
}
