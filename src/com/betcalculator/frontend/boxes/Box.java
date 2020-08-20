package com.betcalculator.frontend.boxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/*
 * Superclass of the boxes classes
 */
public abstract class Box {
	
	VBox root;

	public Box() {
		root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
	}
	
	public double twoDecimalPlaces(double entry) {
		try {
			BigDecimal bigDecimal = new BigDecimal(entry).setScale(2, RoundingMode.HALF_UP);
			double roundedValue = bigDecimal.doubleValue();
			return roundedValue;
		} catch (Exception e) {
			return(0);
		}
	}
	
	public VBox returnBox(){
		return root;
	}
	
}
