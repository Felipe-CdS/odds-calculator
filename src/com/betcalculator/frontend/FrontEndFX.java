package com.betcalculator.frontend;

import com.betcalculator.App;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;

@Getter
public class FrontEndFX extends Application implements Runnable {
	
	
	public static GridTab gridTab;
	LineChartTab lineChartTab;
	ConfigTab configTab;
	VBox rootPane;
	TabPane tabPane;

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
		try {
		rootPane = new VBox();
		rootPane.getStylesheets().add("//stylesheets/styles.css");
		
		tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		
		gridTab = new GridTab("Main");
		lineChartTab = new LineChartTab("graphs");
		configTab = new ConfigTab("config");
		
		tabPane.getTabs().addAll(gridTab.returnTab(), lineChartTab.returnTab(), configTab.returnTab());
		
		rootPane.getChildren().addAll(tabPane);
		
		Scene scene = new Scene(rootPane);
		
		primaryStage.setTitle("test Stage");
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();	
		}
		catch(Exception e) {
			System.out.println(e);
		}
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
	
	@Override
	public void run() {
		
		Runnable updater = new Runnable() {
			@Override
			public void run() {
					gridTab.update();
					lineChartTab.update();
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
