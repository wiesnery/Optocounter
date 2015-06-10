package de.itsw.schaefer.optocounter.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "TimeEntry")
@Table(name = "count")
public class TimeEntry {

	public int channel;

	public LocalDate datum;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public LocalTime time;

	public TimeEntry() {
	}

	public TimeEntry(int channel) {
		this.datum = LocalDate.now();
		this.time = LocalTime.now();

		this.channel = channel;
	}
}
