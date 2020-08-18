package com.betcalculator.frontend.boxes;

import com.betcalculator.App;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class EqualValueBox extends Box {

	public EqualValueBox() {
		super();
		double leftTeamProfitWaste, rightTeamProfitWaste;
		Label titleLabel, leftTeamResultLabel, rightTeamResultLabel;
		
		
		//westBox.setMinSize(250, 200);
		
		leftTeamProfitWaste = App.backendCalcs.finalProfitWaste(App.backendCalcs.getLeftTeamBets(), App.backendCalcs.getLeftTeamOdds(), App.backendCalcs.getRightTeamBets());
		rightTeamProfitWaste = App.backendCalcs.finalProfitWaste(App.backendCalcs.getRightTeamBets(), App.backendCalcs.getRightTeamOdds(), App.backendCalcs.getLeftTeamBets());
		
		titleLabel = new Label("Equal Value:");
		titleLabel.setPrefWidth(250);
		titleLabel.setAlignment(Pos.CENTER);
		
		leftTeamResultLabel = new Label(App.nameTeamOne + " R$ 0,00");
		leftTeamResultLabel.setPrefWidth(250);
		leftTeamResultLabel.setAlignment(Pos.CENTER);
		
		if(leftTeamProfitWaste < 0) {
			leftTeamResultLabel = new Label(App.nameTeamOne + " R$ " 
									+ String.format("%.2f", rightTeamProfitWaste) + " / odd: "	
									+ String.format("%.2f",App.backendCalcs.equalValue()));
		}
		
		rightTeamResultLabel = new Label(App.nameTeamTwo + " R$ 0,00");
		rightTeamResultLabel.setPrefWidth(250);
		rightTeamResultLabel.setAlignment(Pos.CENTER);
		
		if(rightTeamProfitWaste < 0) {
			rightTeamResultLabel = new Label(App.nameTeamTwo + " R$ " 
					+ String.format("%.2f", leftTeamProfitWaste) + " / odd: "	
					+ String.format("%.2f", App.backendCalcs.equalValue()));
		}
		
		root.getChildren().addAll(titleLabel, leftTeamResultLabel, rightTeamResultLabel);
	}

}
