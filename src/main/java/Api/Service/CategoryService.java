package Api.Service;


import Api.Entity.Category;
import Api.JpaRepository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createOrUpdateCategory(Category cat) {
        try {
            categoryRepository.save(cat);
        } catch(Exception e) {
            throw e;
        }
    }

    public void deleteCategory(Category cat) {
        try {
            categoryRepository.delete(cat);
        } catch(Exception e) {
            throw e;
        }
    }
}
