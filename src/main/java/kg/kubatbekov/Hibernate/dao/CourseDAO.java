package kg.kubatbekov.Hibernate.dao;

import kg.kubatbekov.Hibernate.configuration.HibernateSessionFactory;
import kg.kubatbekov.Hibernate.daoInterface.DAO;
import kg.kubatbekov.Hibernate.model.Course;
import kg.kubatbekov.Hibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDAO implements DAO<Course> {
    private final SessionFactory sessionFactory;

    @Autowired
    public CourseDAO(HibernateSessionFactory hibernateSessionFactory) {
        this.sessionFactory = hibernateSessionFactory.getSessionFactory();
    }

    public void addStudent(int course_id, int student_id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Course course = session.find(Course.class, course_id);
        Student student = session.find(Student.class, student_id);

        course.getStudents().add(student);
        session.merge(course);

        session.getTransaction().commit();
    }

    public List<Student> getAllStudents(String courseName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Student> students = session.createQuery("select c from Course c where c.courseName = :course_name", Course.class)
                .setParameter("course_name", courseName)
                .getSingleResult()
                .getStudents();

        session.getTransaction().commit();
        return students;
    }

    @Override
    public List<Course> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Course> courses = session.createQuery("select c from Course c", Course.class)
                .getResultList();

        session.getTransaction().commit();
        return courses;
    }

    @Override
    public void update(Course course) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.merge(course);

        session.getTransaction().commit();
    }

    public void deleteStudent(int course_id, int student_id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Course course = session.find(Course.class, course_id);
        Student student = session.find(Student.class, student_id);

        course.getStudents().remove(student);
        session.merge(course);

        session.getTransaction().commit();
    }

    @Override
    public void deleteById(int course_id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Course course = session.find(Course.class, course_id);
        session.remove(course);

        session.getTransaction().commit();
    }

    @Override
    public void save(Course course) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(course);

        session.getTransaction().commit();
    }

    @Override
    public Optional<Course> getByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Course course = session
                .createQuery("select c from Course c where c.courseId = :course_name", Course.class)
                .setParameter("course_name", name)
                .getSingleResult();
        session.getTransaction().commit();

        return Optional.ofNullable(course);
    }
}
