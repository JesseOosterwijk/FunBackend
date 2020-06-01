package Api.ValidationTests;

import Api.Entity.Role;
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
public class RoleTests {

    public Validator validator;
    public Role testRole = new Role();

    @Before
    public void SetUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        testRole.setId(0);
        testRole.setName("placeholder");
        testRole.setDescription("placeholder");
        testRole.setUsers(new HashSet<>());
    }

    @Test
    public void RoleShouldBeValid()
    {
        Set<ConstraintViolation<Role>> violations = validator.validate(testRole);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void RoleNameShouldBeConstrained()
    {
        testRole.setName("");
        Set<ConstraintViolation<Role>> violations = validator.validate(testRole);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void RoleDescriptionShouldBeConstrained()
    {
        testRole.setDescription("");
        Set<ConstraintViolation<Role>> violations = validator.validate(testRole);
        Assert.assertFalse(violations.isEmpty());
    }
}
