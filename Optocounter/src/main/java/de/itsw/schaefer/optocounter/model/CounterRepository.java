package de.itsw.schaefer.optocounter.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CounterRepository extends CrudRepository<TimeEntry, Long> {
	public List<TimeEntry> findByDatum(Date datum);
	public TimeEntry findFirstByOrderByDatumAsc();
}
