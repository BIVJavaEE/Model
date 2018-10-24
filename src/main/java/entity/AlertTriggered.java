package entity;

import javax.persistence.*;

@Entity
@Table(name = "ALERTS_TRIGGERED")
public class AlertTriggered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private String id;

    @OneToOne
    @JoinColumn(name="id")
    private Alert alert;

    @Basic(optional = false)
    private long triggerDate;

    @Basic(optional = false)
    private long seen;
}
