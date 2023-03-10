package kg.kubatbekov.Hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kg.kubatbekov.Hibernate.daoInterface.DAO;
import kg.kubatbekov.Hibernate.model.Group;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class GroupDAO implements DAO<Group> {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Group group) {
        entityManager.persist(group);
    }

    @Transactional
    @Override
    public Optional<Group> getByName(String name) {
        Group group =
                entityManager.createQuery("select g from Group g where g.group_name = :group_name", Group.class)
                        .setParameter("group_name", name)
                        .getSingleResult();
        return Optional.ofNullable(group);
    }

    @Transactional
    @Override
    public List<Group> getAll() {
        return entityManager.createQuery("select g from Group g", Group.class)
                .getResultList();
    }

    @Transactional
    public List<Group> findLessOrEqualStudentCount(int count) {
        return entityManager.createQuery("select g from Group g", Group.class)
                .getResultStream()
                .filter(group -> group.getStudents().size() <= count & group.getStudents().size() != 0)
                .toList();
    }

    @Transactional
    @Override
    public void update(Group group) {
        entityManager.merge(group);
    }

    @Transactional
    @Override
    public void deleteById(int group_id) {
        Group group = entityManager.find(Group.class, group_id);
        entityManager.remove(group);
    }
}
