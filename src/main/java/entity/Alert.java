package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ALERTS")
public class Alert implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private String id;

    @OneToOne
    private Sensor sensor;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String criticity;

    @Basic(optional = false)
    private Timestamp creationDate;

    @Basic(optional = false)
    private Timestamp beginDate;

    @Basic(optional = false)
    private Timestamp endDate;

    @Basic(optional = false)
    private long treshold;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCriticity() {
        return criticity;
    }

    public void setCriticity(String criticity) {
        this.criticity = criticity;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public long getTreshold() {
        return treshold;
    }

    public void setTreshold(long treshold) {
        this.treshold = treshold;
    }
}
