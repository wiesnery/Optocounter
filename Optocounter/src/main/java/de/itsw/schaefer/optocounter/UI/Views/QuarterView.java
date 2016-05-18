package de.itsw.schaefer.optocounter.UI.Views;

import java.util.Date;

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
	
	private int time;
	private int values;
	
	private int nr;
	private Date start;
	private Date end;
	
	public QuarterView() {
		canvas = new Canvas();
		this.addComponent(canvas);
	}
	
	@PostConstruct
	private void init() {
		Design.read("quarterview.xml", this);
		canvas.setStrokeStyle("grey");
		canvas.moveTo(0, 0);
		canvas.lineTo(0, getHeight());
		canvas.stroke();
		
	}
	
	public void init(int nr, Date start, Date end) {
		this.nr=nr;
		this.start=start;
		this.end=end;
	}
	
	
		
		
}


