package kg.kubatbekov.Hibernate.dao;

import kg.kubatbekov.Hibernate.configuration.HibernateSessionFactory;
import kg.kubatbekov.Hibernate.daoInterface.DAO;
import kg.kubatbekov.Hibernate.model.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GroupDAO implements DAO<Group> {
    private final SessionFactory sessionFactory;

    @Autowired
    public GroupDAO(HibernateSessionFactory hibernateSessionFactory) {
        this.sessionFactory = hibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void save(Group group) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(group);

        session.getTransaction().commit();
    }

    @Override
    public Optional<Group> getByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Group group = session.createQuery("select g from Group g where g.group_name = :group_name", Group.class)
                .setParameter("group_name", name)
                .getSingleResult();

        session.getTransaction().commit();
        return Optional.ofNullable(group);
    }

    @Override
    public List<Group> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Group> groups = session.createQuery("select g from Group g", Group.class)
                .getResultList();

        session.getTransaction().commit();
        return groups;
    }

    public List<Group> findLessOrEqualStudentCount(int count) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Group> groups = session.createQuery("select g from Group g", Group.class)
                .getResultStream()
                .filter(group -> group.getStudents().size() <= count & group.getStudents().size() != 0)
                .toList();

        session.getTransaction().commit();
        return groups;
    }

    @Override
    public void update(Group group) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.merge(group);

        session.getTransaction().commit();
    }

    @Override
    public void deleteById(int group_id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Group group = session.find(Group.class, group_id);
        session.remove(group);

        session.getTransaction().commit();
    }
}
