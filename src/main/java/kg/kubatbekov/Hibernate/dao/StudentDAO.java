package kg.kubatbekov.Hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kg.kubatbekov.Hibernate.daoInterface.DAO;
import kg.kubatbekov.Hibernate.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAO implements DAO<Student> {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Transactional
    @Override
    public List<Student> getAll() {
        return entityManager.createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    @Transactional
    @Override
    public Optional<Student> getByName(String name) {
        Student student =
                entityManager.createQuery("select s from Student s where s.first_name = :first_name", Student.class)
                        .setParameter("first_name", name)
                        .getSingleResult();
        return Optional.ofNullable(student);
    }

    @Transactional
    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Transactional
    @Override
    public void deleteById(int student_id) {
        Student student = entityManager.find(Student.class, student_id);
        entityManager.remove(student);
    }
}
