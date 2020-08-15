package com.betcalculator.frontend;

import javafx.scene.control.Tab;

public class LineChartTab {
	
	Tab lineChartTab;
	LineChartPlotter plotter;
	
	public LineChartTab() {
		lineChartTab = new Tab("graphs");	
		plotter = new LineChartPlotter();
		lineChartTab.setContent(plotter.getLineChart());
	}
	
	public Tab returnTab() {
		return lineChartTab;
	}

	public void update() {
		plotter.plotChart();
	}

}
