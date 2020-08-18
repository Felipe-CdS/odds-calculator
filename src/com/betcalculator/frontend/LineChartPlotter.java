package com.betcalculator.frontend;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.betcalculator.App;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineChartPlotter {
	//final CategoryAxis ;
	final NumberAxis xAxis, yAxis;
	final LineChart<Number, Number> lineChart;
	Series<Number, Number> oddsSeries;
	double plotOdd;
	
	
	public LineChartPlotter(String xAxisName,String yAxisName,String seriesName, String chartName) {
		plotOdd = 0;
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		
		xAxis.setLabel(xAxisName);
		xAxis.setAnimated(false);
		yAxis.setLabel(yAxisName);
		yAxis.setForceZeroInRange(false);
		yAxis.setAnimated(false);
		
		lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setTitle(chartName);
		lineChart.setAnimated(false);
		
		oddsSeries = new XYChart.Series<>();
		oddsSeries.setName(seriesName);
		
		lineChart.getData().add(oddsSeries);
	}
	
	
}
