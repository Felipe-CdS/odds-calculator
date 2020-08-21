package com.betcalculator.frontend.boxes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class EqualValueLiveBox extends Box{

	public EqualValueLiveBox() {
		super();
		//double leftTeamProfitWaste, rightTeamProfitWaste;
		Label titleLabel, teamResultLabel;
	
		/*leftTeamProfitWaste = App.backendCalcs.finalProfitWaste(App.backendCalcs.getLeftTeamBets(), App.backendCalcs.getLeftTeamOdds(), App.backendCalcs.getRightTeamBets());
		rightTeamProfitWaste = App.backendCalcs.finalProfitWaste(App.backendCalcs.getRightTeamBets(), App.backendCalcs.getRightTeamOdds(), App.backendCalcs.getLeftTeamBets());
		*/
		titleLabel = new Label("Live Odd Equal Value:");
		titleLabel.setPrefWidth(250);
		titleLabel.setAlignment(Pos.CENTER);
		
		
		teamResultLabel = new Label("Work in Progress");
		teamResultLabel.setPrefWidth(250);
		teamResultLabel.setAlignment(Pos.CENTER);
		
		/*
		if(leftTeamProfitWaste < 0) {
			teamResultLabel = new Label(App.nameTeamOne + " R$ " 
									+ String.format("%.2f",  App.backendCalcs.equalValueLiveOdd(App.oddTeamOne)) + " / odd: "	
									+ String.format("%.2f", App.oddTeamOne));
		}
		
		if(rightTeamProfitWaste < 0) {
			teamResultLabel = new Label(App.nameTeamTwo + " R$ " 
									+ String.format("%.2f", App.backendCalcs.equalValueLiveOdd(App.oddTeamTwo)) + " / odd: "	
									+ String.format("%.2f", App.oddTeamTwo));
		}*/
		
		root.getChildren().addAll(titleLabel, teamResultLabel);
	}

}
