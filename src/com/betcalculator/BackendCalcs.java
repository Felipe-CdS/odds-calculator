package com.betcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import lombok.Data;

@Data
public class BackendCalcs {
	
	private ArrayList<Double> leftTeamBets;
	private ArrayList<Double> leftTeamOdds;
	
	private ArrayList<Double> rightTeamBets;
	private ArrayList<Double> rightTeamOdds;
	
	public BackendCalcs() {
		leftTeamBets = new ArrayList<Double>();
		leftTeamOdds = new ArrayList<Double>();
		
		rightTeamBets = new ArrayList<Double>();
		rightTeamOdds = new ArrayList<Double>();
	}
	
	public double totalSpent(ArrayList<Double> teamBets) {
		double totalSpent = 0;
		
		for(double i : teamBets) {
			totalSpent += i;
		}
		
		return totalSpent;
	}
	
	public double totalReturn(ArrayList<Double> teamBets, ArrayList<Double> teamOdds) {
		double totalReturn = 0;
		int iteratorCounter = teamBets.size();
		
		for(int i = 0; i < iteratorCounter; i++) {
			totalReturn += (teamBets.get(i) * teamOdds.get(i));
		}
		
		return (twoDecimalPlaces(totalReturn));
	}
	
	public double totalLiquidIncome(ArrayList<Double> teamBets, ArrayList<Double> teamOdds) {
		double totalLiquidIncome = (totalReturn(teamBets, teamOdds) - totalSpent(teamBets));
		return (twoDecimalPlaces(totalLiquidIncome));
	}
	
	public double finalProfitWaste(ArrayList<Double> teamBets, ArrayList<Double> teamOdds, ArrayList<Double> enemyTeamBets) {
		
		Double teamIncome = totalLiquidIncome(teamBets, teamOdds);
		Double enemyTeamWaste = totalSpent(enemyTeamBets);
		Double result = teamIncome - enemyTeamWaste;
		
		return (twoDecimalPlaces(result));
	}

	public double equalValue() {	
		double result = 0;
		double leftTeamFinalResult = twoDecimalPlaces(finalProfitWaste(leftTeamBets, leftTeamOdds, rightTeamBets));
		double rightTeamFinalResult = twoDecimalPlaces(finalProfitWaste(rightTeamBets, rightTeamOdds, leftTeamBets));
		
		
		double bothTeamFinalProfitWasteSum = Math.abs(leftTeamFinalResult) + Math.abs(rightTeamFinalResult);
	
		if(leftTeamFinalResult < 0) {
			result = bothTeamFinalProfitWasteSum / rightTeamFinalResult;
		}
		
		else if(rightTeamFinalResult < 0) {
			result = bothTeamFinalProfitWasteSum / leftTeamFinalResult;	
		}	
		return result;
	}
	
	public void revertTeam(ArrayList<Double> teamBets, ArrayList<Double> teamOdds) {
		try {
			teamBets.remove(teamBets.size()-1);
			teamOdds.remove(teamOdds.size()-1);
		}catch(Exception e) {}
	}
	
	public double twoDecimalPlaces(double entry) {
		BigDecimal bigDecimal = new BigDecimal(entry).setScale(2, RoundingMode.HALF_UP);
		double roundedValue = bigDecimal.doubleValue();
		return roundedValue;
	}
		
}
