package Api.Service;

import Api.Entity.Project;
import Api.Entity.User;
import Api.JpaRepository.ProjectRepository;
import Api.JpaRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public void createOrUpdateProject(Project project) {
        try {
            projectRepository.save(project);
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteProject(Project project) {
        try {
            projectRepository.delete(project);
        } catch(Exception e) {
            throw e;
        }
    }

    public Optional<Set<Project>> GetProjectsByEmail(String email) {
        try {
            User user = userRepository.findUserByEmail(email);
            return projectRepository.findAllByUsers(user);
        } catch(Exception e) {
            throw e;
        }
    }

    public Project GetProjectById(int id) {
        try {
            return projectRepository.findProjectById(id);
        } catch(Exception e) {
            throw e;
        }
    }

    public Project findProjectByNameAndDescription(String name, String description) {
        try {
            return projectRepository.findProjectByNameAndDescription(name, description);
        } catch(Exception e) {
            throw e;
        }
    }

    public void calculatePercentageCategories() {

    }

}
