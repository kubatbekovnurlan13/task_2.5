package kg.kubatbekov.Hibernate.service;

import kg.kubatbekov.Hibernate.dao.GroupDAO;
import kg.kubatbekov.Hibernate.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupDAO groupDAO;

    @Autowired
    public GroupService(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public void save(Group group) {
        groupDAO.save(group);
    }

    public Optional<Group> getByName(String name) {
        return groupDAO.getByName(name);
    }

    public List<Group> findLessOrEqualStudentCount(int count) {
        return groupDAO.findLessOrEqualStudentCount(count);
    }

    public List<Group> getAll() {
        return groupDAO.getAll();
    }

    public void update(Group group) {
        groupDAO.update(group);
    }

    public void deleteById(int group_id) {
        groupDAO.deleteById(group_id);
    }
}
