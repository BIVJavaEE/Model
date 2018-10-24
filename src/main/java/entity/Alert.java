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
    private Sensor sensor;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String criticity;

    @Basic(optional = false)
    private long timestamp;

    @Basic(optional = false)
    private boolean read;

    @Basic(optional = false)
    private long beginDate;

    @Basic(optional = false)
    private long endDate;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public long getTreshold() {
        return treshold;
    }

    public void setTreshold(long treshold) {
        this.treshold = treshold;
    }
}
