package de.itsw.schaefer.optocounter.UI;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

import de.itsw.schaefer.optocounter.UI.Views.TemplateView;

@SpringUI
public class StartUI extends UI {

	private static final long serialVersionUID = 1L;

	TemplateView templateView = new TemplateView();
	Navigator navigator = new Navigator(this, this.templateView.pnContent);

	@Autowired
	SpringViewProvider viewProvider;

	public StartUI() {
	}

	@Override
	protected void init(VaadinRequest request) {
		setContent(this.templateView);

		this.navigator.addProvider(this.viewProvider);

		this.templateView.menu.addItem("Start", (selectedItem) -> {
			this.navigator.navigateTo("");
		});
		this.templateView.menu.addItem("Month", (selectedItem) -> {
			this.navigator.navigateTo("MonthView");
		});
	}

}
