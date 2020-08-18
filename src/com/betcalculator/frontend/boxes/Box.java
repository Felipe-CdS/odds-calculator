package com.betcalculator.frontend.boxes;

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
	
	public VBox returnBox(){
		return root;
	}
	
}
