package de.itsw.schaefer.optocounter.UI.Views;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

@DesignRoot
@SpringView(name = "StatusView")
@Component
public class StatusView extends VerticalLayout implements View {
	
	private Label label;
	private Table table;
	
	@PostConstruct
	private void init() {
		
		Design.read("statusview.xml", this);
	}
	
	public StatusView() {
		table = new Table();
		table.setColumnFooter("Eigenschaft", "Wert");
	}
	
	
	
	@Override
	public void enter(ViewChangeEvent event) {}
	
	

}
