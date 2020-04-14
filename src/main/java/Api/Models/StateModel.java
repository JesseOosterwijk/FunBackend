package Api.Models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class StateModel {

    @NotNull
    private int Id;

    @NotEmpty
    private String Name;

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
