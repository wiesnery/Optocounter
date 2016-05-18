package de.itsw.schaefer.optocounter.UI.Views;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

import de.itsw.schaefer.optocounter.model.CounterRepository;

@DesignRoot
@SpringView(name = "DayView")
@Component
public class DayView extends VerticalLayout implements View {

	@Autowired
	private CounterRepository counterRep;

	@Autowired
	private PrototypeBeanHelper rowHelper;

	private List<HorizontalLayout> vRows = new ArrayList<HorizontalLayout>();

	private static final long serialVersionUID = 1L;
	
	private NativeSelect year;
	private NativeSelect month;
	private NativeSelect day;
	
	

	public DayView() {

	}

	@PostConstruct
	private void init() {
		Design.read("dayview.xml", this);
		
		this.prepareYears();
		
		this.prepareRows();
	}

	private void prepareRows() {
		
		SchmiedehammerRow row = rowHelper.getRowView();
		
		Date date = new Date(2016, 4, 28, 8, 0);
		Date date2 = new Date(2016, 4, 28, 22, 0);
		
		for (int i = 1; i <= 8; i++) {
			if (i == 6) i++;
			
			
			row.init(i, date, date2);
			this.vRows.add(row);
			this.addComponent(row);
		}
	}
	
	public void prepareYears() {
		
		
		
		HorizontalLayout selectorRow = new HorizontalLayout();
		selectorRow.setHeight("10px");
		
		
		int from = 2010; // counterRep.findFirstByOrderByDatumAsc().datum.getYear();
		for (; from <= LocalDate.now().getYear(); from++) {
			year.addItem(from);
			year.setItemCaption(from, Integer.toString(from));
		}
		

		year.select(from - 1);
		month.select(Integer.toString(LocalDate.now().getMonthOfYear()));
		day.select(Integer.toString(LocalDate.now().getDayOfMonth()));

		year.setNullSelectionAllowed(false);
		month.setNullSelectionAllowed(false);
		day.setNullSelectionAllowed(false);
		
		addComponent(selectorRow);
		selectorRow.addComponent(new Label("Jahr: "));
		selectorRow.addComponent(year);
		selectorRow.addComponent(new Label("Monat: "));
		selectorRow.addComponent(month);
		selectorRow.addComponent(new Label("Tag: "));
		selectorRow.addComponent(day);
	}
	/**
	public void fillRows() {
		LocalDate date = new LocalDate(((Integer)this.year.getValue()).intValue(), 
				Integer.parseInt((String) this.month.getValue()),
				Integer.parseInt((String)this.day.getValue()));
		
		for (int i=0; i<8; i++) {
			
		}
	}
	*/

	@Override
	public void enter(ViewChangeEvent event) {
	}
}
