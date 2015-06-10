package de.itsw.schaefer.optocounter.DataListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sun.jna.NativeLibrary;

import de.itsw.schaefer.optocounter.lib.DELIB;
import de.itsw.schaefer.optocounter.model.CounterRepository;
import de.itsw.schaefer.optocounter.model.TimeEntry;

@Component
@Configurable
public class DataListener {

	private long handle = 0;

	private @Autowired CounterRepository repo;

	public DataListener() {
		NativeLibrary.addSearchPath("delib", "C:\\Windows\\SysWOW64\\");
		this.handle = DELIB.INSTANCE.DapiOpenModule(14, 0);
	}

	@Scheduled(fixedDelay = 100)
	public void readInputs() {
		for (int channel = 0; channel < 8; channel++) {
			long count = DELIB.INSTANCE.DapiDIGetCounter(this.handle, channel,
					1);
			if (count >= 1) {
				System.out.println("Added " + count + " on No: " + channel);
				for (long j = 0; j < count; j++) {
					this.repo.save(new TimeEntry(channel));
				}
			}
		}

	}
}
