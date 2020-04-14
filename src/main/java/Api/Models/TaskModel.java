package Api.Models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TaskModel {

    @NotNull
    private int Id;

    @NotEmpty
    private String Name;

    @NotEmpty
    private String Description;

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }
}
