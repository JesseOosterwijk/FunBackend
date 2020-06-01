package Api.ValidationTests;

import Api.Entity.Role;
import Api.Entity.State;
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

import java.util.Set;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
public class StateTests {

    public Validator validator;
    public State testState = new State();

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        testState.setId(0);
        testState.setName("placeholder");
    }

    @Test
    public void StateShouldBeValid()
    {
        Set<ConstraintViolation<State>> violations = validator.validate(testState);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void StateNameShouldBeConstrained()
    {
        testState.setName("");
        Set<ConstraintViolation<State>> violations = validator.validate(testState);
        Assert.assertFalse(violations.isEmpty());
    }
}
