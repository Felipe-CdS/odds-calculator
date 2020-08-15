package com.betcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


import lombok.Data;


/*
 *ATTENTION:
 *This class is an abandoned class. Everything is in BackendClass.java
 */

@Data
public class TeamNumbers {

	private ArrayList<Double> bets;
	private ArrayList<Double> odds;
	private String teamName;
	
	
	public TeamNumbers() {
		bets = new ArrayList<Double>();
		odds = new ArrayList<Double>();
		
	}
	
	public double totalSpent() {
		double totalSpent = 0;
		
		for(double i : this.bets) {
			totalSpent += i;
		}
		
		return totalSpent;
	}
	
	public double totalReturn() {
		double totalReturn = 0;
		
		for(int i = 0; i < bets.size(); i++) {
			totalReturn += (bets.get(i) * odds.get(i));
		}
		
		return(new BigDecimal(totalReturn).setScale(2, RoundingMode.HALF_UP).doubleValue());
	}
	
	public double totalLiquidIncome() {
		double totalLiquidIncome = (this.totalReturn() - this.totalSpent());
		return (new BigDecimal(totalLiquidIncome).setScale(2, RoundingMode.HALF_UP).doubleValue());
	}
	
	public double finalProfitOrWaste(TeamNumbers enemyTeam) {
		double result = totalLiquidIncome() - enemyTeam.totalSpent();
		
		return (new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue());
	}

	public void revertButton() {
		
		
	}
}

