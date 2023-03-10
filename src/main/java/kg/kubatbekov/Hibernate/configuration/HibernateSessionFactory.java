package kg.kubatbekov.Hibernate.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionFactory {
    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateSessionFactory(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("Factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
