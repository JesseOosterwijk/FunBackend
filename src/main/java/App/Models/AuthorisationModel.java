package App.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AuthorisationModel {
    public String getEmail() {
        return email;
    }

    @NotEmpty(message = "Please enter your email")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Please enter your password")
    private String password;
}
