package com.betcalculator.frontend;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.betcalculator.App;

import javafx.geometry.Pos;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;

public class LineChartTab {
	final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
	Tab lineChartTab;
	HBox hbox;
	LineChartPlotter teamOnePlotter, teamTwoPlotter;
	
	public LineChartTab() {
		lineChartTab = new Tab("graphs");	
		teamOnePlotter = new LineChartPlotter("time", "odds", "odds", App.nameTeamOne);
		teamTwoPlotter = new LineChartPlotter("time", "odds", "odds", App.nameTeamTwo);
		hbox = new HBox();
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.getChildren().addAll(teamOnePlotter.getLineChart(), teamTwoPlotter.getLineChart());
		lineChartTab.setContent(hbox);
	}
	
	public Tab returnTab() {
		return lineChartTab;
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
