package com.betcalculator.frontend.boxes;

import com.betcalculator.App;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LiveOddsBox  extends Box {
	
	private HBox liveOddsBox = new HBox();
	double oddTeamOne, oddTeamTwo, percentageTeamOne, percentageTeamTwo;

	public LiveOddsBox() {
		super();
		Label leftTeamNameLabel, rightTeamNameLabel;
		oddTeamOne = twoDecimalPlaces(App.oddTeamOne);
		oddTeamTwo = twoDecimalPlaces(App.oddTeamTwo);
	
		percentageTeamOne = twoDecimalPlaces(100/oddTeamOne);
		percentageTeamTwo = twoDecimalPlaces(100/oddTeamTwo);
		
		liveOddsBox = new HBox();
		liveOddsBox.setSpacing(50);
		liveOddsBox.setAlignment(Pos.TOP_CENTER);
		liveOddsBox.setStyle("-fx-font-size: 25px");
		
		
		
		leftTeamNameLabel = new Label(App.nameTeamOne + ": " + String.valueOf(oddTeamOne) + "x - " + percentageTeamOne + "%");
		rightTeamNameLabel = new Label(App.nameTeamTwo + ": " + String.valueOf(oddTeamTwo)+ "x - " + percentageTeamTwo + "%");
		
		liveOddsBox.getChildren().addAll(leftTeamNameLabel, rightTeamNameLabel);
		root.getChildren().add(liveOddsBox);
	}

}
