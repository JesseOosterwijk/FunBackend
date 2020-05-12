package Api.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "State")
@EntityListeners(AuditingEntityListener.class)
public class State {

    @Id
    private int Id;

    @NotBlank
    private String Name;

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
