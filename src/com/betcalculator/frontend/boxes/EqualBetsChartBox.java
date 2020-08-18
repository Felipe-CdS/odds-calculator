package com.betcalculator.frontend.boxes;

import java.util.ArrayList;

import com.betcalculator.App;
import com.betcalculator.frontend.LineChartPlotter;

import javafx.geometry.Pos;
import javafx.scene.chart.XYChart;

public class EqualBetsChartBox extends Box {
	
	LineChartPlotter lineChart;

	public EqualBetsChartBox() {
		super();
		root.setMinSize(250, 200);
		root.setAlignment(Pos.TOP_CENTER);
		
		lineChart = new LineChartPlotter("value", "odds", "values","Equal bets Chart");
		root.getChildren().add(lineChart.getLineChart());
	}
	
	public void plotGraph() {
		double teamOneSpent = App.backendCalcs.totalSpent(App.backendCalcs.getLeftTeamBets());
		double teamTwoSpent = App.backendCalcs.totalSpent(App.backendCalcs.getRightTeamBets());
		double totalSpent = teamOneSpent + teamTwoSpent;
		for(int value = 1; value < 21; value++) {
			double odds = (value + totalSpent) / value;
			lineChart.getOddsSeries().getData().add(new XYChart.Data<>(value, odds));
		}
	}

}
