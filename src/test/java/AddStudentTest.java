import domain.Student;
import org.junit.*;
import repository.StudentXMLRepository;
import validation.StudentValidator;

import static org.junit.Assert.*;

public class AddStudentTest {

    private StudentXMLRepository srepo;
    private StudentValidator sval;

    @Before
    public void setup() {
        sval = new StudentValidator();
        srepo = new StudentXMLRepository(sval, "studenti.xml");
    }

    @Test
    public void testAddStudentSuccess() {
        Student student = new Student("1", "John Doe", 933);
        srepo.save(student);
        assertNotNull(srepo.findOne("1"));
    }

    @Test
    public void testAddStudentFailure() {
        Student student = new Student("", "John Doe", 933);
        try {
            srepo.save(student);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("ID invalid!\n", e.getMessage());
        }
    }
}