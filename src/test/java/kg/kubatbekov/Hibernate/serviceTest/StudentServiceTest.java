package kg.kubatbekov.Hibernate.serviceTest;

import kg.kubatbekov.Hibernate.container.PostgresContainer;
import kg.kubatbekov.Hibernate.model.Group;
import kg.kubatbekov.Hibernate.model.Student;
import kg.kubatbekov.Hibernate.service.StudentService;
import kg.kubatbekov.Hibernate.service.ValueInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StudentServiceTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private StudentService studentService;

    @Test
    void save_testSave_whenInputNull() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> studentService.save(null));
        assertEquals("attempt to create event with null entity", exception.getMessage());
    }

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = studentService.getAll().size();
        assertEquals(110, actual);
    }

    @Test
    void getByName_testGetByName_whenValueInput() {
        Group group = new Group(1, "group_1");
        Optional<Student> student = Optional.of(new Student(2, "first_2", "last_2", group));

        Optional<Student> actual = studentService.getByName(student.get().getFirstName());
        assertEquals(student, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> studentService.update(null));
        assertEquals("attempt to create merge event with null entity", exception.getMessage());
    }
}
