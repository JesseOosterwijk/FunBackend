package Api.JpaRepository;

import Api.Entity.Category;
import Api.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(int categoryId);
    Category findCategoryByNameAndDescription(String name, String description);
}
