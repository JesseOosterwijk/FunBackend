package Api.Controllers;

import Api.Entity.Category;
import Api.Entity.Project;
import Api.Helpers.Data.TokenData;
import Api.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity SaveCategory(@Valid @RequestBody Category category)
    {
        try {
            categoryService.createOrUpdateCategory(category);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity DeleteCategory(@Valid @RequestBody Category category)
    {
        try {
            categoryService.deleteCategory(category);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity DeleteCategoryFromProject(int catId, @RequestAttribute("user") TokenData user, @PathVariable int id) {
        try {
            Category category = categoryService.findCategoryById(catId);
            categoryService.deleteCategory(category);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
