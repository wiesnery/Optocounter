package de.itsw.schaefer.optocounter.UI.Views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
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

	public DayView() {

	}

	@PostConstruct
	private void init() {
		Design.read("dayview.xml", this);

		this.prepareRows();
	}

	private void prepareRows() {
		for (Integer i = 1; i <= 8; i++) {
			if (i == 6) i++;
			
			SchmiedehammerRow row = rowHelper.getRowView();
			row.setNr(i);
			row.setHours(6,22);
			this.vRows.add(row);
			this.addComponent(row);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
}
