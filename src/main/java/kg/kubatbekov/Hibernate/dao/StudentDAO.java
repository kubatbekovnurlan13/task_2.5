package kg.kubatbekov.Hibernate.dao;

import kg.kubatbekov.Hibernate.configuration.HibernateSessionFactory;
import kg.kubatbekov.Hibernate.daoInterface.DAO;
import kg.kubatbekov.Hibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAO implements DAO<Student> {
    private final SessionFactory sessionFactory;

    @Autowired
    public StudentDAO(HibernateSessionFactory hibernateSessionFactory) {
        this.sessionFactory = hibernateSessionFactory.getSessionFactory();
    }

    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.openSession();

        return session.createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    @Override
    public void save(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(student);

        session.getTransaction().commit();
    }

    @Override
    public Optional<Student> getByName(String name) {
        Session session = sessionFactory.openSession();

        Student student =
                session.createQuery("select s from Student s where s.firstName = :first_name", Student.class)
                        .setParameter("first_name", name)
                        .getSingleResult();

        return Optional.ofNullable(student);
    }

    @Override
    public void update(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.merge(student);

        session.getTransaction().commit();
    }

    @Override
    public void deleteById(int student_id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student student = session.find(Student.class, student_id);
        session.remove(student);

        session.getTransaction().commit();
    }
}
