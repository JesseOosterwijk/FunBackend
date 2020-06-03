package Api.ValidationTests;

import Api.Entity.Project;
import Api.Entity.State;
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
public class TestTask {

    public Validator validator;
    public Task testTask = new Task();

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        testTask.setId(0);
        testTask.setName("placeholder");
        testTask.setDescription("placeholder");
        testTask.setProject(new Project());
        testTask.setCategories(new HashSet<>());
        testTask.setState(new State());
    }

    @Test
    public void TaskShouldBeValid()
    {
        Set<ConstraintViolation<Task>> violations = validator.validate(testTask);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void TaskNameShouldBeConstrained()
    {
        testTask.setName("");
        Set<ConstraintViolation<Task>> violations = validator.validate(testTask);
        Assert.assertFalse(violations.isEmpty());
    }
    @Test
    public void TaskDescriptionShouldBeConstrained()
    {
        testTask.setDescription("");
        Set<ConstraintViolation<Task>> violations = validator.validate(testTask);
        Assert.assertFalse(violations.isEmpty());
    }

}
