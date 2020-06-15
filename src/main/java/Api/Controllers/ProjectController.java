package Api.Controllers;

import Api.Entity.*;
import Api.Helpers.Data.TokenData;
import Api.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/project")
@CrossOrigin("*")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final RoleService roleService;
    private final CategoryService categoryService;
    private final TaskService taskService;

    @Autowired
    public ProjectController(ProjectService projectService, UserService userService, RoleService roleService, CategoryService categoryService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.roleService = roleService;
        this.categoryService = categoryService;
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity SaveProject(@Valid @RequestBody Project project, @RequestAttribute("user") TokenData user)
    {
        try {
            User currentUser = userService.findByEmail(user.getEmail());
            projectService.createOrUpdateProject(project);
            project.addUser(currentUser);
            projectService.createOrUpdateProject(project);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity DeleteProject(@PathVariable int id)
    {
        try {
            Project project = projectService.GetProjectById(id);
            projectService.deleteProject(project);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity GetProjectsByUserEmail(@RequestAttribute("user") TokenData user) {
        try {
            return new ResponseEntity(projectService.GetProjectsByEmail(user.getEmail()), HttpStatus.OK);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity GetProjectById(@RequestAttribute("user") TokenData user, @PathVariable int id) {
        try {
            return new ResponseEntity(projectService.GetProjectById(id), HttpStatus.OK);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/addcategory/{id}")
    public ResponseEntity AddCategoryToProject(@Valid @RequestBody Category category, @RequestAttribute("user") TokenData user, @PathVariable int id) {
        try {
            Project project = projectService.GetProjectById(id);
            categoryService.createOrUpdateCategory(category);
            project.addCategory(category);
            projectService.createOrUpdateProject(project);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/addtask/{id}")
    public ResponseEntity AddTaskToProject(@Valid @RequestBody Task task, @RequestAttribute("user") TokenData user, @PathVariable int id) {
        try {
            Project project = projectService.GetProjectById(id);
            taskService.createOrUpdateTask(task);
            Task taskwithid = taskService.findTaskByNameAndDescription(task.getName(), task.getDescription());
            project.addTask(taskwithid);
            projectService.createOrUpdateProject(project);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/adduser/{id}")
    public ResponseEntity AddUserToProject(@Valid @RequestBody User userToAdd, @RequestAttribute("user") TokenData user, @PathVariable int id) {
        try {
            Project project = projectService.GetProjectById(id);
            project.addUser(userToAdd);
            projectService.createOrUpdateProject(project);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/deleteuser/{id}")
    public ResponseEntity DeleteUserFromProject(@Valid @RequestBody User userToDelete, @RequestAttribute("user") TokenData user, @PathVariable int id) {
        try {
            Project project = projectService.GetProjectById(id);
            project.deleteUser(userToDelete);
            projectService.createOrUpdateProject(project);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/addrole/{id}")
    public ResponseEntity AddRoleToProject(@Valid @RequestBody Role role, @RequestAttribute("user") TokenData user, @PathVariable int id) {
        try {
            Project project = projectService.GetProjectById(id);
            project.addRole(role);
            projectService.createOrUpdateProject(project);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
