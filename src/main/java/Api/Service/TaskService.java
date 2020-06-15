package Api.Service;

import Api.Entity.Category;
import Api.Entity.State;
import Api.Entity.Task;
import Api.JpaRepository.CategoryRepository;
import Api.JpaRepository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
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

    public Optional<Set<Task>> GetTasksByProjectId(int projectId) {
        try {
            return taskRepository.findAllByProjectId(projectId);
        } catch(Exception e) {
            throw e;
        }
    }

    public Optional<Set<Task>> GetTasksByCategoryId(int projectId, int categoryId) {
        try {
            Category category = categoryRepository.findCategoryById(categoryId);
            return taskRepository.findAllByProjectIdAndCategories(projectId, category);
        } catch(Exception e) {
            throw e;
        }
    }

    public Task findTaskById(int id) {
        try {
            return taskRepository.findTaskById(id);
        } catch(Exception e) {
            throw e;
        }
    }

    public Task findTaskByNameAndDescription(String name, String description) {
        try {
            return taskRepository.findTaskByNameAndDescription(name, description);
        } catch(Exception e) {
            throw e;
        }
    }
}
