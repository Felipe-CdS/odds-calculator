package com.betcalculator.frontend.boxes;

import com.betcalculator.App;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LiveOddsBox  extends Box {
	
	private HBox liveOddsBox = new HBox();

	public LiveOddsBox() {
		super();
		Label leftTeamNameLabel, rightTeamNameLabel;
		
		liveOddsBox = new HBox();
		liveOddsBox.setSpacing(50);
		liveOddsBox.setAlignment(Pos.TOP_CENTER);
		liveOddsBox.setStyle("-fx-font-size: 25px");
		
		leftTeamNameLabel = new Label(App.nameTeamOne + " odd: " + String.valueOf(App.oddTeamOne));
		rightTeamNameLabel = new Label(App.nameTeamTwo + " odd: " + String.valueOf(App.oddTeamTwo));
		
		liveOddsBox.getChildren().addAll(leftTeamNameLabel, rightTeamNameLabel);
		root.getChildren().add(liveOddsBox);
	}

}
