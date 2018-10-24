package entity;

import javax.persistence.*;

@Entity
@Table(name = "ALERTS")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
