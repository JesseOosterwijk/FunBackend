package Api.Service;

import Api.Entity.Project;
import Api.JpaRepository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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

    public void calculatePercentageCategories() {

    }

}
