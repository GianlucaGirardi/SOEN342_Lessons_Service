import mapper.HibernateTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Create a session factory and configure it with entity class
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(HibernateTest.class) // Specify the entity class
                .buildSessionFactory();

        // Open a session
        Session session = factory.openSession();

        try {
            // Create an object that will be persisted
            HibernateTest test = new HibernateTest(2L, "Test Name");

            // Start a transaction
            session.beginTransaction();

            // Persist the object (no immediate return of identifier)
            session.persist(test);

            // Commit the transaction (which will insert the record)
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
   }
}

