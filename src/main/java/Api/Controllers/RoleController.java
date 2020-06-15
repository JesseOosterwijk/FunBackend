package Api.Controllers;

import Api.Entity.Category;
import Api.Entity.Role;
import Api.Helpers.Data.TokenData;
import Api.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping()
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

    @DeleteMapping()
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

    @PostMapping("/{id}")
    public ResponseEntity DeleteRoleFromProject(int roleId, @RequestAttribute("user") TokenData user, @PathVariable int id) {
        try {
            Role role = roleService.findRoleById(roleId);
            roleService.deleteRole(role);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
