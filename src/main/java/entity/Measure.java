package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="MEASURES")
public class Measure{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false, insertable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Sensor sensor;

	@Basic(optional = false)
	private double value;

	@Basic(optional = false)
	private Timestamp timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
