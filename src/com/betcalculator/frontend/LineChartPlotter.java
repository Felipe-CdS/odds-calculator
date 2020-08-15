package com.betcalculator.frontend;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.betcalculator.App;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import lombok.Getter;

@Getter
public class LineChartPlotter {
	final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
	final CategoryAxis xAxis;
	final NumberAxis yAxis;
	final LineChart<String, Number> lineChart;
	XYChart.Series<String, Number> series;
	double plotOdd;
	
	
	public LineChartPlotter() {
		plotOdd = 0;
		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		
		xAxis.setLabel("Time/s");
		xAxis.setAnimated(false);
		yAxis.setLabel("Value");
		yAxis.setAnimated(false);
		
		lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setTitle("RealTimeOdds");
		lineChart.setAnimated(false);
		
		series = new XYChart.Series<>();
		series.setName("odds");
		lineChart.getData().add(series);
	}
	
	public void plotChart() {		
		if(plotOdd != App.oddTeamOne) {
			Date now = new Date();
			series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), App.oddTeamOne));
			plotOdd = App.oddTeamOne;
		}
	}
}
