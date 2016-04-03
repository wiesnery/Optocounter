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
public class HourView extends HorizontalLayout {
	
	@Autowired
	public PrototypeBeanHelper helper;
	
	private Canvas canvas;
	
	private static int time;
	private static int values;
	
	
	public HourView() {
		canvas = new Canvas();
		this.addComponent(canvas);
	}
	
	@PostConstruct
	private void init() {
		Design.read("hourview.xml", this);
		canvas.setStrokeStyle("green");
		canvas.moveTo(0, 0);
		canvas.lineTo(0, getHeight());
		canvas.closePath();
		canvas.stroke();

		canvas.setStrokeStyle("black");
		canvas.beginPath();
		canvas.moveTo(0, getHeight());
		canvas.lineTo(getWidth()/6*5, getHeight());
		canvas.stroke();
		canvas.closePath();
		
		canvas.setStrokeStyle("red");
		canvas.moveTo(time, getHeight());
		canvas.lineTo(time, getHeight()-values);
		canvas.stroke();
		canvas.closePath();
		
	}
	
	public void setQuarterHours() {
		for(int i=0; i<3; i++) {
			QuarterView view = helper.getQuarterView();
			this.addComponent(view);
		}
	}
	
	public void fill(int time, int values) {
		this.time=time;
		this.values=values;
	}
	
	
}
