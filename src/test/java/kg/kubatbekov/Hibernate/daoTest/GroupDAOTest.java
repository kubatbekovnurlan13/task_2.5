package kg.kubatbekov.Hibernate.daoTest;

import kg.kubatbekov.Hibernate.container.PostgresContainer;
import kg.kubatbekov.Hibernate.dao.GroupDAO;
import kg.kubatbekov.Hibernate.model.Group;
import kg.kubatbekov.Hibernate.service.ValueInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class GroupDAOTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private GroupDAO groupDAO;

    @Test
    void save_testSave_whenInputNull() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> groupDAO.save(null));
        Assertions.assertEquals("attempt to create event with null entity", exception.getMessage());
    }

    @Test
    void getByName_testGetByName_whenValueInput() {
        Optional<Group> expected = Optional.of(new Group(1, "group_1"));
        Optional<Group> actual = groupDAO.getByName("group_1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = groupDAO.getAll().size();
        Assertions.assertEquals(10, actual);
    }

    @Test
    void findLessOrEqualStudentAccounts_testFindLessOrEqualStudentAccounts_whenThereIsValues() {
        int actual = groupDAO.findLessOrEqualStudentCount(19).size();
        Assertions.assertEquals(3, actual);
    }

    @Test
    void findLessOrEqualStudentAccounts_testFindLessOrEqualStudentAccounts_whenThereIsNoValues() {
        int actual = groupDAO.findLessOrEqualStudentCount(1).size();
        Assertions.assertEquals(0, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> groupDAO.update(null));
        Assertions.assertEquals("attempt to create merge event with null entity", exception.getMessage());
    }
}
