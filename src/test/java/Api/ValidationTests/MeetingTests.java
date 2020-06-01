package Api.ValidationTests;

import Api.Entity.Category;
import Api.Entity.Meeting;
import Api.Entity.Project;
import Api.Entity.User;
import org.apache.tomcat.jni.ProcErrorCallback;
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

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
public class MeetingTests {

    public Validator validator;
    public Meeting testMeeting = new Meeting();

    static final long ONE_MINUTE_IN_MILLIS=60000;
    Calendar date = Calendar.getInstance();
    long t= date.getTimeInMillis();
    Date meetingDate =new Date(t + (10 * ONE_MINUTE_IN_MILLIS));

    @Before
    public void SetUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        testMeeting.setId(0);
        testMeeting.setName("placeholder");
        testMeeting.setDescription("placeholder");
        testMeeting.setDate(meetingDate);
        testMeeting.setProject(new Project());
    }

    @Test
    public void MeetingShouldBeValid()
    {
        Set<ConstraintViolation<Meeting>> violations = validator.validate(testMeeting);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void MeetingNameShouldBeConstrained()
    {
        testMeeting.setName("a");
        Set<ConstraintViolation<Meeting>> violations = validator.validate(testMeeting);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void MeetingDescriptionShouldBeConstrained()
    {
        testMeeting.setDescription("short");
        Set<ConstraintViolation<Meeting>> violations = validator.validate(testMeeting);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void MeetingDateShouldBeConstrained()
    {
        Date meetingDate =new Date(t - (10 * ONE_MINUTE_IN_MILLIS));
        testMeeting.setDate(meetingDate);
        Set<ConstraintViolation<Meeting>> violations = validator.validate(testMeeting);
        Assert.assertFalse(violations.isEmpty());
    }
}
