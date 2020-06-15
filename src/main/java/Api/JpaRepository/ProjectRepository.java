package Api.JpaRepository;

import Api.Entity.Project;
import Api.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Set<Project>> findAllByUsers(User user);
    Project findProjectById(int id);
    Project findProjectByNameAndDescription(String name, String description);
}
