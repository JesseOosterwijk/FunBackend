package Api.Controllers;

import Api.Controllers.Enums.Response;
import Api.Entity.User;
import Api.Helpers.Data.TokenData;
import Api.Helpers.Requests.LoginRequest;
import Api.Helpers.Requests.RegisterRequest;
import Api.Helpers.Requests.VerifyRequest;
import Api.Helpers.Responses.LoginResponse;
import Api.Helpers.Responses.RegisterResponse;
import Api.Models.AuthorisationModel;
import Api.Models.UserRegisterModel;
import Api.Service.AuthService;
import Api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.validation.Valid;


@RestController
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest request) {
        try {
            this.authService.register(request);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse jwt;
        try {
            jwt = this.authService.login(request);
        }
        catch(AuthenticationException ex) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/verify")
    public ResponseEntity<TokenData> verify(@RequestBody VerifyRequest request) {
        TokenData tokenData;
        try {
            tokenData = this.authService.verify(request.getToken());
        }
        catch(AuthenticationException ex) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tokenData, HttpStatus.OK);
    }

}
