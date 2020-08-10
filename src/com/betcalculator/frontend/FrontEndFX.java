package com.betcalculator.frontend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import com.betcalculator.App;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FrontEndFX extends Application implements Runnable {
	
	BorderPane root;
	HBox topBox, centerBox;
	VBox westBox, southBox, eastBox;

	/*
	 * Implements the inherited method init from Application.
	 * Here the thread is started so it's possible to make later modifications in the canvas 
	 * while the program is running.
	 */
	@Override
	public void init() {
		Thread thread = new Thread(this);
		thread.start();
	}

	/*
	 * Implements the inherited method start from Application.
	 * Here everything is started. The root Pane starts, a method for 
	 * each part is called and the parts are added to the root.
	 * The root pane is a borderlayout, so it is divided in north, east, south, west and center.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
				
		root = new BorderPane();
		root.getStylesheets().add("//stylesheets/styles.css");
		
		topBox();
		westBox();
		eastBox();
		centerBox();
		southBox();

		root.setTop(topBox);
		root.setLeft(westBox);
		root.setRight(eastBox);
		root.setCenter(centerBox);
		root.setBottom(southBox);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("test Stage");
		primaryStage.setWidth(1000);
		primaryStage.setHeight(500);
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	/*
	 * Implements the inherited method stop from Application.
	 * Just closes the webdriver when the canvas is closed and
	 * the process is finished.
	 */
	@Override
	public void stop() {
		App.closeBrowser();
		System.exit(0);
	}
	
	/*
	 * Everything about the center part of the root pane is made here.
	 * This part, called centerBox, is divided in two smaller vertical boxes, the teamOrganizers.
	 * Each one of these teamOrganizers is divided into more two smaller boxes. 
	 * A gridPane to organize the oddsList, valueList, totalSpent, totalReturn and liquidIncome in a grid 
	 * and a horizontal box to organize the entry text fields and buttons.
	 * 
	 */
	public void centerBox() {
		VBox leftTeamOrganizer, rightTeamOrganizer;
		
		centerBox = new HBox();
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setSpacing(15);

		leftTeamOrganizer = centerBoxTeamOrganizer(App.backendCalcs.getLeftTeamBets(), App.backendCalcs.getLeftTeamOdds());
		rightTeamOrganizer = centerBoxTeamOrganizer(App.backendCalcs.getRightTeamBets(), App.backendCalcs.getRightTeamOdds());
		
		centerBox.getChildren().addAll(leftTeamOrganizer, rightTeamOrganizer);
	}
	
	/*
	 * Everything about the top part of the root pane is made here.
	 * This part, called topBox, has the actual Live odds of both teams provided by the App class.
	 * 
	 */
	public void topBox() {
		Label leftTeamNameLabel, rightTeamNameLabel;
		
		topBox = new HBox();
		topBox.setSpacing(50);
		topBox.setAlignment(Pos.BASELINE_CENTER);

		leftTeamNameLabel = new Label(App.nameTeamOne + " odd: " + String.valueOf(App.oddTeamOne));
		rightTeamNameLabel = new Label(App.nameTeamTwo + " odd: " + String.valueOf(App.oddTeamTwo));
		
		topBox.getChildren().addAll(leftTeamNameLabel, rightTeamNameLabel);	
	}
	
	/*
	 * Everything about the left part of the root pane is made here.
	 * This part, called westBox, controls the screen part that shows how
	 * to equal the bet.
	 * 
	 */
	public void westBox() {
		
		double leftTeamProfitWaste, rightTeamProfitWaste;
		Label titleLabel, leftTeamResultLabel, rightTeamResultLabel;
		
		westBox = new VBox();
		westBox.setMinSize(250, 200);
		westBox.setAlignment(Pos.TOP_CENTER);
		
		leftTeamProfitWaste = App.backendCalcs.finalProfitOrWaste(App.backendCalcs.getLeftTeamBets(), App.backendCalcs.getLeftTeamOdds(), App.backendCalcs.getRightTeamBets());
		rightTeamProfitWaste = App.backendCalcs.finalProfitOrWaste(App.backendCalcs.getRightTeamBets(), App.backendCalcs.getRightTeamOdds(), App.backendCalcs.getLeftTeamBets());
		
		titleLabel = new Label("Equal Value:");
		titleLabel.setPrefWidth(250);
		titleLabel.setAlignment(Pos.CENTER);
		
		leftTeamResultLabel = new Label(App.nameTeamTwo + " R$ 0,00");
		leftTeamResultLabel.setPrefWidth(250);
		leftTeamResultLabel.setAlignment(Pos.CENTER);
		
		if(leftTeamProfitWaste < 0) {
			leftTeamResultLabel = new Label(App.nameTeamOne + " R$ " 
									+ String.format("%.2f", rightTeamProfitWaste) + " / odd: "	
									+ String.format("%.2f",App.backendCalcs.getEqualValueOdd()));
		}
		
		rightTeamResultLabel = new Label(App.nameTeamTwo + " R$ 0,00");
		rightTeamResultLabel.setPrefWidth(250);
		rightTeamResultLabel.setAlignment(Pos.CENTER);
		
		if(rightTeamProfitWaste < 0) {
			rightTeamResultLabel = new Label(App.nameTeamTwo + " R$ " 
					+ String.format("%.2f", leftTeamProfitWaste) + " / odd: "	
					+ String.format("%.2f", App.backendCalcs.getEqualValueOdd()));
		}
		
		
		
		westBox.getChildren().addAll(titleLabel, leftTeamResultLabel, rightTeamResultLabel);
	}
	
	public void eastBox() {
		eastBox = new VBox();
		eastBox.setMinSize(250, 200);
	}

	public void southBox() {
		HashMap<Label, Double> teamResultLabels = new HashMap<Label, Double>();
		double leftTeamProfitWaste, rightTeamProfitWaste;
		Label winnerLabel, leftTeamResultLabel, rightTeamResultLabel;
		
		
		leftTeamProfitWaste = App.backendCalcs.finalProfitOrWaste(App.backendCalcs.getLeftTeamBets(), App.backendCalcs.getLeftTeamOdds(), App.backendCalcs.getRightTeamBets());
		rightTeamProfitWaste = App.backendCalcs.finalProfitOrWaste(App.backendCalcs.getRightTeamBets(), App.backendCalcs.getRightTeamOdds(), App.backendCalcs.getLeftTeamBets());
		
		southBox = new VBox();
		southBox.setAlignment(Pos.TOP_CENTER);
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
		
		southBox.getChildren().addAll(winnerLabel, resultsGrid);
		
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
		});
		
		revertButton = new Button("Revert");
		revertButton.setMaxSize(100, 20);	
		
		revertButton.setOnAction(e -> {	
			numbersGrid.getChildren().clear();
			App.backendCalcs.revertTeam(teamBets, teamOdds);
			teamGridGenerator(teamBets, teamOdds, numbersGrid);
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
	
	@Override
	public void run() {
		
		Runnable updater = new Runnable() {
			@Override
			public void run() {
				topBox.getChildren().clear();
				topBox();
				root.setTop(topBox);
				
				southBox.getChildren().clear();
				southBox();
				root.setBottom(southBox);
				
				westBox.getChildren().clear();
				westBox();
				root.setLeft(westBox);
			}	
		};
		
		while(true) {
			try {
				Thread.sleep(500);		
			}catch(Exception e) {}
			Platform.runLater(updater);
		}
	}
}
