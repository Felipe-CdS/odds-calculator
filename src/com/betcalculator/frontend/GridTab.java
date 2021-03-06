package com.betcalculator.frontend;

import com.betcalculator.frontend.boxes.ChartKeyPointsBox;
import com.betcalculator.frontend.boxes.EntriesGridBox;
import com.betcalculator.frontend.boxes.EqualBetsChartBox;
import com.betcalculator.frontend.boxes.EqualValueBox;
import com.betcalculator.frontend.boxes.EqualValueLiveBox;
import com.betcalculator.frontend.boxes.FinalResultBox;
import com.betcalculator.frontend.boxes.LiveOddsBox;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GridTab extends AbstractTab {
	
	VBox rootPane, leftBox, rightBox;
	HBox rootPaneOrganizer;
	
	LiveOddsBox liveOddsBox;
	EntriesGridBox entriesGridBox;
	EqualValueBox equalValueBox;
	FinalResultBox finalResultBox;
	EqualBetsChartBox equalBetsChartBox;
	ChartKeyPointsBox chartKeyPointsBox;
	EqualValueLiveBox equalValueLiveBox;

	//This tab is build with 3 parts. The top one whit the odds, the left and the right;
	public GridTab(String name) {
		super(name);
		rootPane = new VBox();
		rootPane.setAlignment(Pos.TOP_CENTER);
		
		liveOddsBox = new LiveOddsBox();
		leftBoxContructor();
		rightBoxConstructor();
		
		rootPaneOrganizer = new HBox();
		rootPaneOrganizer.setAlignment(Pos.CENTER);
		rootPaneOrganizer.getChildren().addAll(leftBox, rightBox);
		
		rootPane.getChildren().addAll(liveOddsBox.returnBox(), rootPaneOrganizer);

		rootTab.setContent(rootPane);
		
	}
	
	//The leftBox has the entriesGridBox, the equalValueBox and the finalResultBox;
	private void leftBoxContructor() {
		leftBox = new VBox();
		leftBox.setAlignment(Pos.TOP_CENTER);
		leftBox.setSpacing(50);
		HBox minorBoxesOrganizer = new HBox();
		minorBoxesOrganizer.setAlignment(Pos.TOP_CENTER);
		
		entriesGridBox = new EntriesGridBox();
		equalValueBox = new EqualValueBox();
		finalResultBox = new FinalResultBox();
		equalValueLiveBox = new EqualValueLiveBox();

		minorBoxesOrganizer.getChildren().addAll(finalResultBox.returnBox(), equalValueBox.returnBox(), equalValueLiveBox.returnBox());
		leftBox.getChildren().addAll(entriesGridBox.returnBox(), minorBoxesOrganizer);
	}
	
	//The rightBox has the equalBetsChartBox, the chartKeyPointsBox and the equalValueLiveBox;
	private void rightBoxConstructor() {
		rightBox = new VBox();
		rightBox.setAlignment(Pos.TOP_CENTER);
		HBox minorBoxesOrganizer = new HBox();
		minorBoxesOrganizer.setAlignment(Pos.TOP_CENTER);
				
		equalBetsChartBox = new EqualBetsChartBox();
		chartKeyPointsBox = new ChartKeyPointsBox(equalBetsChartBox.returnChart());
		
		
		minorBoxesOrganizer.getChildren().addAll(chartKeyPointsBox.returnBox());
		rightBox.getChildren().addAll(equalBetsChartBox.returnBox(), minorBoxesOrganizer);
	}
	
	/*
	 * This method is called when the Entry or Revert buttons is clicked
	 * and updates almost all the tab.
	 */
	public void entryUpdate() {
		rootPane.getChildren().clear();
		rootPaneOrganizer.getChildren().clear();
		
		leftBoxContructor();
		rightBoxConstructor();
		
		equalBetsChartBox.plotGraph();
		rootPaneOrganizer.getChildren().addAll(leftBox, rightBox);
		rootPane.getChildren().addAll(liveOddsBox.returnBox(), rootPaneOrganizer);
	}
	
	/*
	 * This method is called every second by the FrontEndFX class.
	 * This special feature is only needed by the boxes that are about the live odds
	 * because the odd changes every second.
	 */
	public void update() {
		liveOddsBox = new LiveOddsBox();
		//equalValueLiveBox = new EqualValueLiveBox();
		rootPane.getChildren().remove(0);
		rootPane.getChildren().add(0, liveOddsBox.returnBox());
	}
	
}
