package Api.Entity;

import java.util.UUID;

public class User {
    private UUID id;
    private String email;

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private String password;
}
