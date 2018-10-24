package entity;

import javax.persistence.*;

@Entity
@Table(name = "ALERTS")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private String id;

    @OneToOne
    @JoinColumn(name="id")
    private Sensor sensor;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String criticity;

    @Basic(optional = false)
    private long creationDate;

    @Basic(optional = false)
    private long beginDate;

    @Basic(optional = false)
    private long endDate;

    @Basic(optional = false)
    private long treshold;
}
