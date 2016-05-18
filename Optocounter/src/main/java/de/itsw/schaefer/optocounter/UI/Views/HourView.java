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
public class HourView extends HorizontalLayout {
	
	@Autowired
	public PrototypeBeanHelper helper;
	
	private Canvas canvas;
	
	
	
	private static int time;
	private static int values;
	
	private int nr;
	private Date start;
	private Date end;
	
	public HourView() {
		canvas = new Canvas();
		this.addComponent(canvas);
	}
	
	@PostConstruct
	private void init() {
		Design.read("hourview.xml", this);
		
		//Stundenzeiger
		canvas.setStrokeStyle("green");
		canvas.moveTo(0, 0);
		canvas.lineTo(0, getHeight());
		canvas.closePath();
		canvas.stroke();
		
		//Untere Grenze
		canvas.setStrokeStyle("black");
		canvas.beginPath();
		canvas.moveTo(0, getHeight());
		canvas.lineTo(getWidth()/6*5, getHeight());
		canvas.closePath();
		canvas.stroke();
		
		//Viertelstunden
		for(int i=0; i<4; i++) {
			canvas.setStrokeStyle("grey");
			canvas.beginPath();
			canvas.moveTo(getWidth()/5*i, 0);
			canvas.lineTo(getWidth()/5*i, getHeight());
			canvas.closePath();
			canvas.stroke();
		}
		
		//Hammerschläge
		canvas.setStrokeStyle("red");
		canvas.beginPath();
		canvas.moveTo(time, getHeight());
		canvas.lineTo(time, getHeight()-values);
		canvas.stroke();
		
	}
	
	public void init(int nr, Date start, Date end) {
		this.nr=nr;
		this.start=start;
		this.end=end;
	}
	
	
	public void fill(int time, int values) {
		this.time=time;
		this.values=values;
	}
	
	
}
