package de.itsw.schaefer.optocounter.UI.Views;

import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.elasticsearch.common.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.stereotype.Component;
import org.vaadin.hezamu.canvas.Canvas;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.declarative.Design;

import de.itsw.schaefer.optocounter.model.CounterRepository;
import de.itsw.schaefer.optocounter.model.DataManager;

@SuppressWarnings("serial")
@DesignRoot
@Component
@Scope("prototype")
public class SchmiedehammerRow extends HorizontalLayout {

	private Label nr;
	private Label sum;

	@Autowired
	private CounterRepository repo;
	@Autowired
	private PrototypeBeanHelper helper;
	@Autowired
	private DataManager datamanager;
	
	
	
	Random rand = new Random();

	public SchmiedehammerRow() {
		
	}

	@PostConstruct
	private void init() {
		Design.read("schmiedehammerrow.xml", this);
		
	}
	
	@SuppressWarnings("deprecation")
	public void init(int nr, Date start, Date end) {
		
		HourView view = helper.getHourView();
		HorizontalLayout values = new HorizontalLayout();
		
		this.setNr(nr);
		for(int i = 1;i<=end.getHours()-start.getHours();i++) {
			
			view.init(nr, new DateTime(start).plusHours(i).toDate(), new DateTime(start).plusHours(i+1).toDate());
			this.fillRows(view);
			values.addComponent(view);
		}
	}

	private void fillValues(Date date) {
		this.sum.setValue("37");
	}

	public void setNr(Integer nr) {
		this.nr.setValue("<h1>" + nr.toString() + "</h1>");
	}
	
	/**
	 * Ähnlich wie "fillTable()" in MonthView, werden hier zufällige Werte erzeugt, um die Rows zu füllen.
	 * Die Methode muss wahrscheinlich gelöscht werden, wenn SchmiedehammerRow die richtigen Werte erhält.
	 */
	public void fillRows(HourView view) {
		
		for (int i=0; i<14; i++) {
			view.fill(rand.nextInt(37), rand.nextInt(37));
		}
	}

	/**
	public void setHours(int begin, int end) {
		for(int i = 1;i<=end-begin;i++) {
			HourView view = helper.getHourView();
			view.setId(Integer.toString(i));
			//view.fill(rand.nextInt(76), datamanager.getValue(date));
			//changes to view
			this.values.addComponent(view);
		}
		
	}
	*/
	
	

}
