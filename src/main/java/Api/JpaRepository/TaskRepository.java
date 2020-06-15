package Api.JpaRepository;

import Api.Entity.Category;
import Api.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Set<Task>> findAllByProjectId(int projectId);
    Optional<Set<Task>> findAllByProjectIdAndCategories(int projectId, Category category);
    Task findTaskById(int id);
    Task findTaskByNameAndDescription(String name, String description);
}
