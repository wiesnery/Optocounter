package de.itsw.schaefer.optocounter.UI.Views;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class PrototypeBeanHelper {
	
	@Lookup
	public SchmiedehammerRow getRowView() {
		return null;}
	
	@Lookup
	public HourView getHourView() {
		return null;}

}
