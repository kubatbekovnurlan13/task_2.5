package kg.kubatbekov.Hibernate.serviceTest;

import kg.kubatbekov.Hibernate.container.PostgresContainer;
import kg.kubatbekov.Hibernate.model.Course;
import kg.kubatbekov.Hibernate.service.CourseService;
import kg.kubatbekov.Hibernate.service.ValueInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CourseServiceTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private CourseService courseService;

    @Test
    void save_testSave_whenNullInput() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> courseService.save(null));
        Assertions.assertEquals("attempt to create event with null entity", exception.getMessage());
    }

    @Test
    void addStudent_testAddStudentToCourse_whenCourseWrongValueInput() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> courseService.addStudent(1000,20));
        Assertions.assertEquals("Cannot invoke \"kg.kubatbekov.Hibernate.model.Course.getStudents()\" because \"course\" is null", exception.getMessage());
    }

    @Test
    void getAllStudents_testGetAllStudentsOfCourse_whenThereIsCourseName() {
        int actual = courseService.findAllStudents("course_1").size();
        Assertions.assertEquals(4, actual);
    }

    @Test
    void getAllStudents_testGetAllStudentsOfCourse_whenThereIsWrongCourseName() {
        Exception exception = assertThrows(EmptyResultDataAccessException.class,
                () -> courseService.getByName("wrong_name"));
        Assertions.assertEquals("No result found for query [select c from Course c where c.courseName = :course_name]", exception.getMessage());

    }

    @Test
    void getAll_testGetAllCourses_whenThereIsCourses() {
        int actual = courseService.getAll().size();
        Assertions.assertEquals(10, actual);
    }

    @Test
    void getByName_testGetCourseByName_whenCourseNameInput() {
        Optional<Course> course = Optional.of(new Course(7,"course_7", "course_description_7"));

        Optional<Course> actual = courseService.getByName(course.get().getCourseName());
        Assertions.assertEquals(course, actual);
    }

    @Test
    void getByName_testGetCourseByName_whenWrongCourseNameInput() {
        Exception exception = assertThrows(EmptyResultDataAccessException.class,
                () -> courseService.getByName("wrong_name"));
        Assertions.assertEquals("No result found for query [select c from Course c where c.courseName = :course_name]", exception.getMessage());

    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> courseService.update(null));
        Assertions.assertEquals("attempt to create merge event with null entity", exception.getMessage());
    }
}
