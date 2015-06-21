package de.itsw.schaefer.optocounter.UI.Views;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.declarative.Design;

import de.itsw.schaefer.optocounter.model.CounterRepository;

@SuppressWarnings("serial")
@DesignRoot
@Component
@Scope("prototype")
public class SchmiedehammerRow extends HorizontalLayout {

	private Label nr;
	private HorizontalLayout values;
	private Label sum;

	@Autowired
	private CounterRepository repo;
	@Autowired
	private PrototypeBeanHelper helper;

	public SchmiedehammerRow() {

	}

	@PostConstruct
	private void init() {
		Design.read("schmiedehammerrow.xml", this);
	}

	private void fillValues() {
		this.sum.setValue("37");
	}

	public void setNr(Integer nr) {
		this.nr.setValue("<h1>" + nr.toString() + "</h1>");
		this.fillValues();
	}

	public void setHours(int begin, int end) {
		for(int i = 1;i<=end-begin;i++) {
			HourView view = helper.getHourView();
			//changes to view
			this.values.addComponent(view);
		}
		
	}

}
