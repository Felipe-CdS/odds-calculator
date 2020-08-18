package com.betcalculator.frontend.boxes;

import java.util.ArrayList;
import java.util.Collections;

import com.betcalculator.App;
import com.betcalculator.frontend.FrontEndFX;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EntriesGridBox extends Box{
	
	private HBox entriesGrid;

	public EntriesGridBox() {
		super();
		VBox leftTeamOrganizer, rightTeamOrganizer;
		
		entriesGrid = new HBox();
		entriesGrid.setAlignment(Pos.TOP_CENTER);
		entriesGrid.setSpacing(15);

		leftTeamOrganizer = centerBoxTeamOrganizer(App.backendCalcs.getLeftTeamBets(), App.backendCalcs.getLeftTeamOdds());
		rightTeamOrganizer = centerBoxTeamOrganizer(App.backendCalcs.getRightTeamBets(), App.backendCalcs.getRightTeamOdds());
		
		
		entriesGrid.getChildren().addAll(leftTeamOrganizer, rightTeamOrganizer);
		root.getChildren().add(entriesGrid);
	}

	public void teamGridGenerator(ArrayList<Double> teamBets, ArrayList<Double> teamOdds, GridPane teamGrid) {
		ArrayList<Label> labelList;
		Label oddsColumn, valueColumn, totalSpent, totalSpentCalc, totalReturn, totalReturnCalc, totalLiquid, totalLiquidCalc;
		
		labelList = new ArrayList<Label>();
		
		oddsColumn = new Label("Odds:");
		valueColumn = new Label("Value:");
		totalSpent = new Label("Total Spent:");
		totalSpentCalc = new Label("R$ " + String.format("%.2f", App.backendCalcs.totalSpent(teamBets)));
		totalReturn = new Label("Total Return:");
		totalReturnCalc = new Label("R$ " + String.format("%.2f", App.backendCalcs.totalReturn(teamBets, teamOdds)));
		totalLiquid = new Label("Liquid Income:");
		totalLiquidCalc = new Label("R$ " + String.format("%.2f", App.backendCalcs.totalLiquidIncome(teamBets, teamOdds)));
		
		Collections.addAll(labelList, oddsColumn, valueColumn, totalSpent, totalSpentCalc, totalReturn, totalReturnCalc, totalLiquid, totalLiquidCalc);
		
		for(Label i : labelList) {
			i.setId("gridLabel");
		}
		
		teamGrid.add(oddsColumn, 0, 0);
		teamGrid.add(valueColumn, 1, 0);
		teamGrid.add(totalSpent, 2, 0);	
		teamGrid.add(totalSpentCalc, 2, 1);	
		teamGrid.add(totalReturn, 2, 2);	
		teamGrid.add(totalReturnCalc, 2, 3);	
		teamGrid.add(totalLiquid, 2, 4);
		teamGrid.add(totalLiquidCalc, 2, 5);

		for(int i = 1; i < 6; i++) {
			Label holder;
			try {
				holder = new Label(String.valueOf(teamOdds.get(i-1)));	
			}catch(Exception e) {
				holder = new Label("");
			}
			
			holder.setId("gridLabel");		
			teamGrid.add(holder, 0, i);
		}

		for(int i = 1; i < 6; i++) {
			Label holder;
			try {
				holder = new Label(String.valueOf(teamBets.get(i-1)));
			}catch(Exception e) {
				holder = new Label("");
			}
			holder.setId("gridLabel");
			teamGrid.add(holder, 1, i);
		}
	}
		
	public VBox centerBoxTeamOrganizer(ArrayList<Double> teamBets, ArrayList<Double> teamOdds) {
		VBox teamOrganizer;
		HBox entryFields;
		GridPane numbersGrid;
		TextField teamBetsEntry, teamOddsEntry;
		Button enterButton, revertButton;
		
		teamOrganizer = new VBox();
		teamOrganizer.setAlignment(Pos.TOP_CENTER);

		
		numbersGrid = new GridPane();
		numbersGrid.setAlignment(Pos.TOP_CENTER);

		teamGridGenerator(teamBets, teamOdds, numbersGrid);
		
		teamBetsEntry = new TextField();
		teamBetsEntry.setMaxSize(100, 20);	
		
		teamOddsEntry = new TextField();
		teamOddsEntry.setMaxSize(100, 20);	
		
		enterButton = new Button("Enter");
		enterButton.setMaxSize(100, 20);	
		
		enterButton.setOnAction(e -> {
			numbersGrid.getChildren().clear();
			
			try { 
				teamBets.add(Double.valueOf(textTreatement(teamBetsEntry.getText())));	
				teamOdds.add(Double.valueOf(textTreatement(teamOddsEntry.getText())));	
				
			}catch(Exception x) {}
			
			teamBetsEntry.clear();
			teamOddsEntry.clear();
			
			teamGridGenerator(teamBets, teamOdds, numbersGrid);
			FrontEndFX.gridTab.entryUpdate();
		});
		
		revertButton = new Button("Revert");
		revertButton.setMaxSize(100, 20);	
		
		revertButton.setOnAction(e -> {	
			numbersGrid.getChildren().clear();
			App.backendCalcs.revertTeam(teamBets, teamOdds);
			teamGridGenerator(teamBets, teamOdds, numbersGrid);
			FrontEndFX.gridTab.entryUpdate();
			//eastBox.getChildren().clear();
			//eastBox();
			//betsGridPane.add(eastBox, 1, 1);
			
		});
				
		entryFields = new HBox();
		entryFields.getChildren().addAll(teamOddsEntry, teamBetsEntry, enterButton, revertButton);
		teamOrganizer.getChildren().addAll(numbersGrid, entryFields);
		
		return teamOrganizer;
	}
	
	public String textTreatement(String text) {
		String returnText = text;
		
		if(returnText.contains(",")) {
			returnText = returnText.replaceAll("," , ".");
		}

		return returnText;
	}
}
