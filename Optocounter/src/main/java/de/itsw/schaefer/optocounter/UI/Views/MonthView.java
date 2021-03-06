package de.itsw.schaefer.optocounter.UI.Views;

import javax.annotation.PostConstruct;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

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

	@Autowired
	private CounterRepository counterRep;

	private static final long serialVersionUID = 1L;

	public MonthView() {

	}

	@PostConstruct
	private void init() {
		Design.read("monthview.xml", this);

		this.prepareTable();
		this.prepareYears();

		this.fillTable();
	}

	private void prepareYears() {

		int from = 2010; // counterRep.findFirstByOrderByDatumAsc().datum.getYear();
		for (; from <= LocalDate.now().getYear(); from++) {
			this.year.addItem(from);
			this.year.setItemCaption(from, Integer.toString(from));
		}

		this.year.select(from - 1);
		this.month.select(Integer.toString(LocalDate.now().getMonthOfYear()));

		this.year.setNullSelectionAllowed(false);
		this.month.setNullSelectionAllowed(false);

		this.year.addValueChangeListener((l) -> this.dateChanged());
		this.month.addValueChangeListener((l) -> this.dateChanged());

	}

	private void dateChanged() {
		this.table.removeAllItems();
		this.fillTable();

	}

	@SuppressWarnings("unchecked")
	private void fillTable() {
		LocalDate date = new LocalDate(
				((Integer) this.year.getValue()).intValue(),
				Integer.parseInt((String) this.month.getValue()), 1);
		LocalDate nextMonth = date.plusMonths(1);
		while (date.isBefore(nextMonth)) {
			Item item = this.table.addItem(date.dayOfMonth().get());
			item.getItemProperty("Tag").setValue(date.dayOfWeek().getAsText());
			item.getItemProperty("Datum").setValue(date.toString());

			for (Integer i = 1; i <= 8; i++) {
				if (i == 6) i++;

				Property<Integer> property = item.getItemProperty("H "
						+ i.toString());
				property.setValue(37 * i * date.getMonthOfYear()
						* date.getDayOfYear());
			}

			date = date.plusDays(1);
			
		}
		
		int actualRowCount = date.minusDays(1).getDayOfMonth();
		table.setPageLength(actualRowCount);

		this.table.setColumnFooter("Datum", "Summe");

		for (Integer i = 1; i <= 8; i++) {
			if (i == 6) i++;

			Integer sum = 0;
			for (Object id : this.table.getItemIds())
				sum += (Integer) this.table.getContainerProperty(id,
						"H " + i.toString()).getValue();
			this.table.setColumnFooter("H " + i.toString(), sum.toString());
		}
	}

	private void prepareTable() {
		this.table.setSelectable(true);
		this.table.setImmediate(true);
		this.table.setFooterVisible(true);

		this.table.addContainerProperty("Tag", String.class, null);
		this.table.addContainerProperty("Datum", String.class, null);
		for (Integer i = 1; i <= 8; i++) {
			if (i == 6) i++;

			this.table.addContainerProperty("H " + i.toString(), Integer.class,
					null);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
}
