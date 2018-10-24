package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "PREDICATES")
public class Predicate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private String id;

    @OneToOne
    private Comparator comparator;

    @Basic
    private String type;

    @Basic
    private double value;

    @Basic
    private Timestamp beginDate;

    @Basic
    private Timestamp endDate;

}
