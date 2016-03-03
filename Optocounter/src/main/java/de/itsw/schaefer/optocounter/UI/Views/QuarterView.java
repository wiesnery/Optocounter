package de.itsw.schaefer.optocounter.UI.Views;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.hezamu.canvas.Canvas;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.declarative.Design;

@SuppressWarnings("serial")
@Component
@DesignRoot
@Scope("prototype")
public class QuarterView extends HorizontalLayout {
	
	private Canvas canvas;
	
	public QuarterView() {
		canvas = new Canvas();
		this.addComponent(canvas);
	}
	
	@PostConstruct
	private void init() {
		Design.read("quarterview.xml", this);
		canvas.setFillStyle("green");
		canvas.fillRect(10, 10, 20, 20);
	}
	
	
		
		
}


