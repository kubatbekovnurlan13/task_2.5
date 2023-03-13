package kg.kubatbekov.Hibernate.service;

import kg.kubatbekov.Hibernate.dao.CourseDAO;
import kg.kubatbekov.Hibernate.model.Course;
import kg.kubatbekov.Hibernate.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseDAO courseDAO;

    @Autowired
    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void save(Course course) {
        courseDAO.save(course);
    }

    public List<Student> findAllStudents(String courseName) {
        return courseDAO.getAllStudents(courseName);
    }

    public void addStudent(int course_id, int student_id) {
        courseDAO.addStudent(course_id, student_id);
    }

    public List<Course> getAll() {
        return courseDAO.getAll();
    }

    public Optional<Course> getByName(String name) {
        return courseDAO.getByName(name);
    }

    public void update(Course course) {
        courseDAO.update(course);
    }

    public void deleteById(int course_id) {
        courseDAO.deleteById(course_id);
    }

    public void deleteStudent(int course_id, int student_id) {
        courseDAO.deleteStudent(course_id, student_id);
    }
}
