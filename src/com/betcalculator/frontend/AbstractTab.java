package com.betcalculator.frontend;

import javafx.scene.control.Tab;

public abstract class AbstractTab {
	
	Tab rootTab;

	public AbstractTab(String name) {
		rootTab = new Tab(name);
	}
	
	public Tab returnTab() {
		return rootTab;
	}

}
