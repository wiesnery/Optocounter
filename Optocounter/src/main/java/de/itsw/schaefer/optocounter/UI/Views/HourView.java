package de.itsw.schaefer.optocounter.UI.Views;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.declarative.Design;

@SuppressWarnings("serial")
@Component
@DesignRoot
public class HourView extends HorizontalLayout {
	
	public HourView() {
	}
	
	@PostConstruct
	private void init() {
		Design.read("hourview.xml", this);
	}
}
