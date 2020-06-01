package Api.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "State")
@EntityListeners(AuditingEntityListener.class)
public class State {

    @Id
    @Column(name = "Id")
    private int Id;

    @NotBlank
    @Column(name = "Name")
    @Size(min = 2)
    private String Name;

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
