package Api.Controllers;

import Api.Entity.Category;
import Api.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/saveCategory")
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

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/deleteCategory")
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
}
