package de.itsw.schaefer.optocounter.UI.Views;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

@DesignRoot
@Component
@Scope("prototype")
public class TemplateView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public MenuBar menu;
	public Panel pnContent;

	public TemplateView() {
	}

	@PostConstruct
	private void init() {
		Design.read("template.xml", this);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}