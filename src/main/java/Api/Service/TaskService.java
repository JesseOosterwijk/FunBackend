package Api.Service;

import Api.Entity.State;
import Api.Entity.Task;
import Api.JpaRepository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createOrUpdateTask(Task task) {
        try {
            taskRepository.save(task);
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteTask(Task task) {
        try {
            taskRepository.delete(task);
        } catch(Exception e) {
            throw e;
        }
    }
}
