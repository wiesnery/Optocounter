package de.itsw.schaefer.optocounter.UI.Views;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

@DesignRoot
@SpringView(name = "")
public class StartView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	static final String name = "";

	public StartView() {
		Design.read("startview.xml", this);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}
}
