package Api.Controllers;

import Api.Entity.Role;
import Api.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/saveRole")
    public ResponseEntity SaveRole(@Valid @RequestBody Role role)
    {
        try {
            roleService.createOrUpdateRole(role);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/deleteRole")
    public ResponseEntity DeleteRole(@Valid @RequestBody Role role)
    {
        try {
            roleService.deleteRole(role);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
