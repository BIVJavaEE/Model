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

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Alert alert;

    @Basic(optional = false)
    private Timestamp triggerDate;

    @Basic(optional = false)
    private boolean seen;

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
