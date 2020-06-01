package Api.ValidationTests;

import Api.Entity.Category;
import Api.Entity.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
public class CategoryTests {

    public Validator validator;
    public Category testCategory = new Category();

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        testCategory.setDescription("placeholder");
        testCategory.setName("placeholder");
        testCategory.setId(0);
        testCategory.setTasks(new HashSet<Task>());
    }

    @Test
    public void CategoryShouldBeValid()
    {
        Set<ConstraintViolation<Category>> violations = validator.validate(testCategory);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void CategoryNameShouldBeConstrained()
    {
        testCategory.setName("1");
        Set<ConstraintViolation<Category>> violations = validator.validate(testCategory);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void CategoryDescriptionShouldBeConstrained()
    {
        testCategory.setDescription("short");
        Set<ConstraintViolation<Category>> violations = validator.validate(testCategory);
        Assert.assertFalse(violations.isEmpty());
    }
}
