package Api.ValidationTests;

import Api.Entity.Meeting;
import Api.Entity.Project;
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
public class TestProject {

    public Validator validator;
    public Project testProject = new Project();

    @Before
    public void SetUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        testProject.setId(0);
        testProject.setName("placeholder");
        testProject.setDescription("placeholder");
        testProject.setTasks(new HashSet<>());
        testProject.setUsers(new HashSet<>());
    }

    @Test
    public void ProjectShouldBeValid()
    {
        Set<ConstraintViolation<Project>> violations = validator.validate(testProject);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void ProjectNameShouldBeConstrained()
    {
        testProject.setName("");
        Set<ConstraintViolation<Project>> violations = validator.validate(testProject);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void ProjectDescriptionShouldBeConstrained()
    {
        testProject.setDescription("");
        Set<ConstraintViolation<Project>> violations = validator.validate(testProject);
        Assert.assertFalse(violations.isEmpty());
    }
}
