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
            // Create an object that will be persisted (UUID is generated in the constructor)
            HibernateTest test = new HibernateTest("Test Namess");

            // Start a transaction
            session.beginTransaction();

            // Persist the object
            session.save(test);

            // Commit the transaction (which will insert the record)
            session.getTransaction().commit();
        } finally {
            factory.close();  //close the factory to release resources
        }
    }
}

