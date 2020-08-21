package com.betcalculator.frontend;

import com.betcalculator.App;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConfigTab extends AbstractTab {
	
	VBox rootPane;
	HBox matchEntry;

	
	public ConfigTab(String name) {
		super(name);
		rootPane = new VBox();
		rootPane.setAlignment(Pos.TOP_CENTER);
		rootPane.setSpacing(100);
		matchEntry();
		rootPane.getChildren().add(matchEntry);
		
		rootTab.setContent(rootPane);
		
	}
	
	public void matchEntry() {
		matchEntry = new HBox();
		
		Label matchURL = new Label("Match URL:");
		TextField textField = new TextField();
		
		Button entryButton = new Button("Enter");
		
		entryButton.setOnAction( e-> {
			App.matchURL =  textField.getText();
			textField.clear();
			
			if(App.matchURL != null && App.driver != null) {
				App.driver.get(App.matchURL);
			}
			
		});
		matchEntry.getChildren().addAll(matchURL, textField, entryButton);
	}		
}
