package mapper;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class HibernateTest {

    @Id
    private long testId;
    private String name;

    public HibernateTest(long testId, String name){
        this.testId = testId;
        this.name = name;
    }


}
