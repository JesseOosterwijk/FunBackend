package Api.Controllers;

import Api.Controllers.Enums.Response;
import Api.Entity.User;
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
import java.util.Optional;


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
        try {
            User user = userService.findbyEmail(authModel.getEmail())
                    .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.WRONG_CREDENTIALS.toString()); });
            if(!authModel.getPassword().equals(user.getPassword())) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(authModel);
        } catch(Exception ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserRegisterModel regModel) {
        if(userService.findbyEmail(regModel.getEmail()).isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try {
            User user = new User();
            user.setEmail(regModel.getEmail());
            user.setName(regModel.getName());
            user.setPassword(regModel.getPassword());

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
