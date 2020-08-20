package com.betcalculator.frontend;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.betcalculator.App;

import javafx.geometry.Pos;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;

public class LineChartTab extends AbstractTab {
	
	final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
	HBox hbox;
	LineChartPlotter teamOnePlotter, teamTwoPlotter;
	
	public LineChartTab(String name) {
		super(name);
		teamOnePlotter = new LineChartPlotter("time", "odds", "odds", App.nameTeamOne);
		teamTwoPlotter = new LineChartPlotter("time", "odds", "odds", App.nameTeamTwo);
		hbox = new HBox();
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.getChildren().addAll(teamOnePlotter.getLineChart(), teamTwoPlotter.getLineChart());
		rootTab.setContent(hbox);
	}
	
	public void update() {
		this.plotChart(teamOnePlotter, App.oddTeamOne);
		this.plotChart(teamTwoPlotter, App.oddTeamTwo);
	}
	
	public void plotChart(LineChartPlotter lineChart, double teamOdds) {
		if(lineChart.getPlotOdd() != teamOdds) {
			Date now = new Date();
			lineChart.getOddsSeries().getData().add(new XYChart.Data<>(1, teamOdds));
			lineChart.setPlotOdd(teamOdds);
		}
	}

}
