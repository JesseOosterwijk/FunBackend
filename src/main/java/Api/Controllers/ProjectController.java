package Api.Controllers;

import Api.Entity.Project;
import Api.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/saveProject")
    public ResponseEntity SaveProject(@Valid @RequestBody Project project)
    {
        try {
            projectService.createOrUpdateProject(project);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/deleteProject")
    public ResponseEntity DeleteProject(@Valid @RequestBody Project project)
    {
        try {
            projectService.deleteProject(project);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
