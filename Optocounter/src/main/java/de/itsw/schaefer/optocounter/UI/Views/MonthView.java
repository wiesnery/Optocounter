package de.itsw.schaefer.optocounter.UI.Views;

import org.joda.time.LocalDate;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

import de.itsw.schaefer.optocounter.model.CounterRepository;



@DesignRoot
@SpringView(name = "MonthView")
public class MonthView extends VerticalLayout implements View {
	
	private Table table;
	private NativeSelect year;
	private NativeSelect month;
	
	private CounterRepository counterRep;

	private static final long serialVersionUID = 1L;
	
	public MonthView() {
		Design.read("monthview.xml", this);
		
		prepareTable();
		prepareYears();
		
		fillTable();
	}

	private void prepareYears() {
		int from = counterRep.findFirstByOrderByDatumAsc().datum.getYear();
		for(;from <= LocalDate.now().getYear(); from++) {
			year.addItem(from);
			year.setItemCaption(from, Integer.toString(from));
		}
		
		for(int i = 1; i<=12;i++) {
			month.addItem(i);
			month.setItemCaption(i,Integer.toString(i));
		}
		
	}

	@SuppressWarnings("unchecked")
	private void fillTable() {
		LocalDate date = new LocalDate().withDayOfMonth(1);
		LocalDate nextMonth = date.plusMonths(1);
		while(date.isBefore(nextMonth)) {
			Item item = table.addItem(date.dayOfMonth().get());
			item.getItemProperty("Tag").setValue(date.dayOfWeek().getAsText());
			item.getItemProperty("Datum").setValue(date.toString());
			
			for(Integer i = 1; i<7 ;i++) {
				if (i == 6) {i++; break;}
				Property<Integer> property = item.getItemProperty("H " + i.toString());
				property.setValue(37*i);
			}
			
			date = date.plusDays(1);
		}
		
		table.setColumnFooter("Datum", "Summe");
		for(Integer i = 1; i<7 ;i++) {
			if (i == 6) {i++; break;}
			table.setColumnFooter("H " + i.toString(), Integer.toString(37*i*31));
		}
	}

	private void prepareTable() {
		table.setWidth("100%");
		table.setSelectable(false);
		table.setImmediate(true);
		table.setFooterVisible(true);
		
		table.addContainerProperty("Tag", String.class, null);
		table.addContainerProperty("Datum", String.class, null);
		for(Integer i = 1; i<7 ;i++) {
			if (i == 6) {i++; break;}
			table.addContainerProperty("H " + i.toString(), Integer.class, null);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
}
