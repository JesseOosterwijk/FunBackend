package Api.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import Api.Entity.Project;

@Entity
@Table(name = "Meeting")
@EntityListeners(AuditingEntityListener.class)
public class Meeting {

    @Id
    private int Id;

    @NotBlank
    private String Name;

    @NotBlank
    private String Description;

    @NotNull
    private Date Date;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project Project;

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public Date getDate() {
        return Date;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public Project getProject() {
        return Project;
    }

    public void setProject(Project project) {
        Project = project;
    }
}
