package mapper;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "hibernate_test") // Specify the table name explicitly (defaults to HibernateTest if not specified)
public class HibernateTest {

    @Id  // This annotation marks the field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate ID with auto-incrementing value
    @Column(name = "id")  // Column name for the ID field in the database
    private Long id;  // Use Long for numeric ID (can also use Integer if preferred)

    @Column(name = "name") // Explicitly map the name field to the "name" column in the database
    private String name;

    // Default constructor for Hibernate
    public HibernateTest() {
    }

    // Constructor to accept name and generate the ID automatically
    public HibernateTest(String name) {
        this.name = name;
    }

    // Getters and setters for the fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
