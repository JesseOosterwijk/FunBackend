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
        try {
            userService.login(authModel.getEmail(), authModel.getPassword());
            return ResponseEntity.ok(authModel);
        } catch(Exception ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserRegisterModel regModel) {
        try {
            userService.register(regModel.getEmail(), regModel.getName(), regModel.getPassword());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
