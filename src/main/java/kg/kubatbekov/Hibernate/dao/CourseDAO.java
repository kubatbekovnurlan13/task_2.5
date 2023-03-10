package kg.kubatbekov.Hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kg.kubatbekov.Hibernate.daoInterface.DAO;
import kg.kubatbekov.Hibernate.model.Course;
import kg.kubatbekov.Hibernate.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDAO implements DAO<Course> {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Transactional
    public void addStudent(int course_id, int student_id) {
        Course course = entityManager.find(Course.class, course_id);
        Student student = entityManager.find(Student.class, student_id);
        course.getStudents().add(student);
        entityManager.merge(course);
    }

    @Transactional
    public List<Student> getAllStudents(String courseName) {
        return entityManager.createQuery("select c from Course c where c.course_name = :course_name", Course.class)
                .setParameter("course_name", courseName)
                .getSingleResult()
                .getStudents();
    }

    @Transactional
    @Override
    public List<Course> getAll() {
        return entityManager.createQuery("select c from Course c", Course.class)
                .getResultList();
    }

    @Transactional
    @Override
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Transactional
    public void deleteStudent(int course_id, int student_id) {
        Course course = entityManager.find(Course.class, course_id);
        Student student = entityManager.find(Student.class, student_id);
        course.getStudents().remove(student);
        entityManager.merge(course);
    }

    @Transactional
    @Override
    public void deleteById(int course_id) {
        Course course = entityManager.find(Course.class, course_id);
        entityManager.remove(course);
    }

    @Transactional
    @Override
    public Optional<Course> getByName(String name) {
        Course course =
                entityManager.createQuery("select c from Course c where c.course_name = :course_name", Course.class)
                        .setParameter("course_name", name)
                        .getSingleResult();
        return Optional.ofNullable(course);
    }
}
