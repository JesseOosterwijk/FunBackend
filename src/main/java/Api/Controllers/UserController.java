package Api.Controllers;

import Api.Entity.User;
import Api.Controllers.Enums.Response;
import Api.Models.AuthorisationModel;
import Api.Models.UserRegisterModel;
import Api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthorisationModel authModel) {
        User user = userService.findByEmail(authModel.getEmail())
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.WRONG_CREDENTIALS.toString()); });

        if (true) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.WRONG_CREDENTIALS.toString());
        }

        try {
            return ResponseEntity.ok(authModel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserRegisterModel regModel) {
        if (userService.findByEmail(regModel.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.USER_ALREADY_EXISTS.toString());
        }

        try {
            User user = new User();
            return ResponseEntity.ok(regModel);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }
    }
}
