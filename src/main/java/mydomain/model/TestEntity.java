package mydomain.model;

import org.joda.time.LocalDate;
import javax.jdo.annotations.*;

@PersistenceCapable(table = "TEST_ENTITY")
public class TestEntity {
    
    @Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
    
    @Persistent
    @Column(allowsNull = "false")
    private LocalDate testDate;

    public TestEntity() {}
    
    public TestEntity(LocalDate testDate) {
        this.testDate = testDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDate getTestDate() { return testDate; }
    public void setTestDate(LocalDate testDate) { this.testDate = testDate; }
}
