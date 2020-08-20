package com.betcalculator.frontend.boxes;

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
		double leftTeamProfitWaste, rightTeamProfitWaste, leftTeamSpent, rightTeamSpent;
		
		leftTeamProfitWaste = App.backendCalcs.finalProfitWaste(App.backendCalcs.getLeftTeamBets(), App.backendCalcs.getLeftTeamOdds(), App.backendCalcs.getRightTeamBets());
		rightTeamProfitWaste = App.backendCalcs.finalProfitWaste(App.backendCalcs.getRightTeamBets(), App.backendCalcs.getRightTeamOdds(), App.backendCalcs.getLeftTeamBets());
		leftTeamSpent = App.backendCalcs.totalSpent(App.backendCalcs.getLeftTeamBets());
		rightTeamSpent = App.backendCalcs.totalSpent(App.backendCalcs.getRightTeamBets());
		
		for(int value = 1; value <= Math.max(leftTeamProfitWaste, rightTeamProfitWaste); value++) {
			double odds = (value + Math.max(leftTeamSpent, rightTeamSpent)) / value;
			lineChart.getOddsSeries().getData().add(new XYChart.Data<>(value, odds));
		}
	}

}
