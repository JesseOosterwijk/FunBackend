package Api.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import Api.Entity.Project;

@Entity
@Table(name = "Meeting")
@EntityListeners(AuditingEntityListener.class)
public class Meeting {

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

    @NotNull
    @Column(name = "Date")
    @Future
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

    public void setId(int id) {
        Id = id;
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
