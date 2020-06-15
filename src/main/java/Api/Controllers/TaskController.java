package Api.Controllers;

import Api.Entity.Category;
import Api.Entity.Task;
import Api.Helpers.Data.TokenData;
import Api.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/task")
@CrossOrigin("*")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity SaveTask(@Valid @RequestBody Task task)
    {
        try {
            taskService.createOrUpdateTask(task);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity DeleteTask(@Valid @RequestBody Task task)
    {
        try {
            taskService.deleteTask(task);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity DeleteTaskFromProject(int taskId, @RequestAttribute("user") TokenData user, @PathVariable int id) {
        try {
            Task task = taskService.findTaskById(taskId);
            taskService.deleteTask(task);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
