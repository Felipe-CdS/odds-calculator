package com.betcalculator.frontend.boxes;

import java.util.ArrayList;
import java.util.HashMap;

import com.betcalculator.App;
import com.betcalculator.frontend.LineChartPlotter;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ChartKeyPointsBox extends Box {

	GridPane rootGrid;
	HashMap<Double, Double> keyPoints;
	ArrayList<Label> oddsLabelsList;
	ArrayList<Label> valuesLabelsList;
	
	public ChartKeyPointsBox(LineChartPlotter chart) {
		super();
		
		rootGrid = new GridPane();	
		keyPoints = new HashMap<Double, Double>();
		oddsLabelsList = new ArrayList<Label>();
		valuesLabelsList = new ArrayList<Label>();
		
		for(int i = 0; i < 10; i++) {
			oddsLabelsList.add(new Label(" "));
			valuesLabelsList.add(new Label(" "));
		}
		
		keyPoints();
	
		for(Label i : oddsLabelsList) {
			i.setId("gridLabel");
		}
		for(Label i : valuesLabelsList) {
			i.setId("gridLabel");
		}
		
		Label subTitle1 = new Label("Odds");
		subTitle1.setStyle("-fx-border-style: solid; -fx-alignment: center; -fx-font-size: 10px; -fx-min-width: 100; -fx-min-height: 15;");
		Label subTitle2 = new Label("Values");
		subTitle2.setStyle("-fx-border-style: solid; -fx-alignment: center; -fx-font-size: 10px; -fx-min-width: 100; -fx-min-height: 15;");
		
		rootGrid.add(subTitle1, 0, 0);
		rootGrid.add(subTitle2, 1, 0);
		
		int index = 0;
		for(int i = 1; i < oddsLabelsList.size()+1; i++) {
			rootGrid.add(oddsLabelsList.get(index), 0, i);
			rootGrid.add(valuesLabelsList.get(index), 1, i);
			index++;
		}
		
		Label title = new Label("10 chart key points");
		
		title.setStyle("-fx-border-style: solid; -fx-alignment: center; -fx-font-size: 10px; -fx-min-width: 200; -fx-min-height: 15;");
		root.getChildren().addAll(title, rootGrid);
	}
	
	private void keyPoints() {
		double leftTeamProfitWaste, rightTeamProfitWaste, leftTeamSpent, rightTeamSpent;
		
		leftTeamProfitWaste = App.backendCalcs.finalProfitWaste(App.backendCalcs.getLeftTeamBets(), App.backendCalcs.getLeftTeamOdds(), App.backendCalcs.getRightTeamBets());
		rightTeamProfitWaste = App.backendCalcs.finalProfitWaste(App.backendCalcs.getRightTeamBets(), App.backendCalcs.getRightTeamOdds(), App.backendCalcs.getLeftTeamBets());
		leftTeamSpent = App.backendCalcs.totalSpent(App.backendCalcs.getLeftTeamBets());
		rightTeamSpent = App.backendCalcs.totalSpent(App.backendCalcs.getRightTeamBets());
		
		if(Math.min(leftTeamProfitWaste, rightTeamProfitWaste) < 0) {
			
			double increment = 1;
			if(Math.max(leftTeamProfitWaste, rightTeamProfitWaste) > 10) {
				increment = Math.max(leftTeamProfitWaste, rightTeamProfitWaste) / 10;
			}
			
			for(double value = 1; value <= Math.max(leftTeamProfitWaste, rightTeamProfitWaste); value += increment) {
				double odds = (value + Math.max(leftTeamSpent, rightTeamSpent)) / value;
				oddsLabelsList.remove(0);
				valuesLabelsList.remove(0);
				oddsLabelsList.add(new Label(String.valueOf(App.backendCalcs.twoDecimalPlaces(value))));
				valuesLabelsList.add(new Label(String.valueOf(App.backendCalcs.twoDecimalPlaces(odds))));
			}			
		}	
	}
}
