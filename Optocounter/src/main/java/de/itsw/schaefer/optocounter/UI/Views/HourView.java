package de.itsw.schaefer.optocounter.UI.Views;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.declarative.Design;

@SuppressWarnings("serial")
@Component
@DesignRoot
@Scope("prototype")
public class HourView extends HorizontalLayout {
	
	@Autowired
	private PrototypeBeanHelper helper;
	
	
	public HourView() {
		
	}
	
	@PostConstruct
	private void init() {
		Design.read("hourview.xml", this);
	}
	
	public void setQuarterHours() {
		for(int i=0; i<4; i++) {
			QuarterView view = helper.getQuarterView();
			this.addComponent(view);
		}
	}
}
