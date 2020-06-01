package Api.ValidationTests;

import Api.Entity.Project;
import Api.Entity.Role;
import Api.Entity.User;
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
import java.util.UUID;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
public class UserTests {

    public Validator validator;
    User testUser = new User();

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        testUser.setId(UUID.randomUUID());
        testUser.setName("placeholder");
        testUser.setEmail("placeholder@outlook.com");
        testUser.setPassword("secret");
        Project project = new Project();
        Set<Project> projects = new HashSet<Project>();
        projects.add(project);
        testUser.setProjects(projects);
        Role role = new Role();
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        testUser.setRoles(roles);
    }

    @Test
    public void UserShouldBeValid()
    {
        Set<ConstraintViolation<User>> violations = validator.validate(testUser);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void InvalidEmailShouldBeConstrained()
    {
        testUser.setEmail("invalidEmail");
        Set<ConstraintViolation<User>> violations = validator.validate(testUser);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void InvalidNameShouldBeConstrained()
    {
        testUser.setName("A");
        Set<ConstraintViolation<User>> violations = validator.validate(testUser);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void InvalidPasswordShouldBeConstrained()
    {
        testUser.setPassword("123");
        Set<ConstraintViolation<User>> violations = validator.validate(testUser);
        Assert.assertFalse(violations.isEmpty());
    }
}
