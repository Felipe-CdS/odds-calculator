package com.betcalculator.frontend.boxes;

import java.util.HashMap;
import java.util.Map.Entry;

import com.betcalculator.App;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FinalResultBox extends Box {
	
	public FinalResultBox() {
		super();
		HashMap<Label, Double> teamResultLabels = new HashMap<Label, Double>();
		double leftTeamProfitWaste, rightTeamProfitWaste;
		Label winnerLabel, leftTeamResultLabel, rightTeamResultLabel;
		
		
		leftTeamProfitWaste = App.backendCalcs.finalProfitWaste(App.backendCalcs.getLeftTeamBets(), App.backendCalcs.getLeftTeamOdds(), App.backendCalcs.getRightTeamBets());
		rightTeamProfitWaste = App.backendCalcs.finalProfitWaste(App.backendCalcs.getRightTeamBets(), App.backendCalcs.getRightTeamOdds(), App.backendCalcs.getLeftTeamBets());
		
		GridPane resultsGrid = new GridPane();
		resultsGrid.setAlignment(Pos.TOP_CENTER);
		
		winnerLabel = new Label("Final Result:");

		leftTeamResultLabel = new Label(App.nameTeamOne + " R$ " + String.format("%.2f", leftTeamProfitWaste));
		rightTeamResultLabel = new Label(App.nameTeamTwo + " R$ " + String.format("%.2f", rightTeamProfitWaste));
		
		teamResultLabels.put(leftTeamResultLabel, leftTeamProfitWaste);
		teamResultLabels.put(rightTeamResultLabel, rightTeamProfitWaste);
		
		
		for(Entry<Label, Double> i : teamResultLabels.entrySet()) {
			
			i.getKey().setPrefWidth(250);
			i.getKey().setAlignment(Pos.CENTER);
			
			if(i.getValue() < 0) {
				i.getKey().setStyle("-fx-background-color: Red;");
			}
			else if(i.getValue() > 0) {
				
				i.getKey().setStyle("-fx-background-color: Green;");
			}
		}

		resultsGrid.add(leftTeamResultLabel, 0, 0);
		resultsGrid.add(rightTeamResultLabel, 0, 1);
		
		root.getChildren().addAll(winnerLabel, resultsGrid);
	}
	
}
