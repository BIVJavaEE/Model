package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ALERTS_TRIGGERED")
public class AlertTriggered{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Alert alert;

    @Basic(optional = false)
    private Timestamp triggerDate;

    @Basic(optional = false)
    private boolean seen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public Timestamp getTriggerDate() {
        return triggerDate;
    }

    public void setTriggerDate(Timestamp triggerDate) {
        this.triggerDate = triggerDate;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
