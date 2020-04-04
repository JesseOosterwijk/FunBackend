package Api.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserRegisterModel {
    public String getEmail() {
        return email;
    }

    @NotEmpty(message = "Please enter your email")
    @Email(message = "invalid email")
    private String email;

    @NotEmpty(message = "Please enter a password")
    private String password;
}
