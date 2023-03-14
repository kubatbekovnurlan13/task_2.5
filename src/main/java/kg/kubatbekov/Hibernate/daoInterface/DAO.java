package kg.kubatbekov.Hibernate.daoInterface;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    void save(T t);

    Optional<T> getByName(String name);

    List<T> getAll();

    void update(T t);

    void deleteById(int id);
}
