package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ALERTS_TRIGGERED")
public class AlertTriggered{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(optional = false)
    private Alert alert;

    @Basic(optional = false)
    private boolean seen;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Measure measure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}
