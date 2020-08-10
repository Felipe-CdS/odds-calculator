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
	
	private double equalValueOdd = 0;
	
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
		
		return (new BigDecimal(totalReturn).setScale(2, RoundingMode.HALF_UP).doubleValue());
	}
	
	public double totalLiquidIncome(ArrayList<Double> teamBets, ArrayList<Double> teamOdds) {
		double totalLiquidIncome = (totalReturn(teamBets, teamOdds) - totalSpent(teamBets));
		return (new BigDecimal(totalLiquidIncome).setScale(2, RoundingMode.HALF_UP).doubleValue());
	}
	
	public double finalProfitOrWaste(ArrayList<Double> teamBets, ArrayList<Double> teamOdds, ArrayList<Double> enemyTeamBets) {
		
		Double teamIncome = totalLiquidIncome(teamBets, teamOdds);
		Double enemyTeamWaste = totalSpent(enemyTeamBets);
		Double result = teamIncome - enemyTeamWaste;
		
		return (new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue());
	}

	public void equalValue() {
		double bothTeamFinalProfitWasteSum = Math.abs(finalProfitOrWaste(leftTeamBets, leftTeamOdds, rightTeamBets)) + Math.abs(finalProfitOrWaste(rightTeamBets, rightTeamOdds, leftTeamBets));
		
		BigDecimal leftTeamFinalResult = new BigDecimal(finalProfitOrWaste(leftTeamBets, leftTeamOdds, rightTeamBets)).setScale(2, RoundingMode.HALF_UP);
		BigDecimal rightTeamFinalResult = new BigDecimal(finalProfitOrWaste(rightTeamBets, rightTeamOdds, leftTeamBets)).setScale(2, RoundingMode.HALF_UP);
		
		if(leftTeamFinalResult.doubleValue() < 0) {
			BigDecimal rightTeamLiquidProfit = new BigDecimal(finalProfitOrWaste(rightTeamBets, rightTeamOdds, leftTeamBets)).setScale(2, RoundingMode.HALF_UP);
			double result = bothTeamFinalProfitWasteSum / rightTeamLiquidProfit.doubleValue();
			equalValueOdd = result;
		}
		
		else if(rightTeamFinalResult.doubleValue() < 0) {
			BigDecimal leftTeamLiquidProfit = new BigDecimal(finalProfitOrWaste(leftTeamBets, leftTeamOdds, rightTeamBets)).setScale(2, RoundingMode.HALF_UP);
			double result = bothTeamFinalProfitWasteSum / leftTeamLiquidProfit.doubleValue();
			equalValueOdd = result;
			
		}
	}
	
	public void revertTeam(ArrayList<Double> teamBets, ArrayList<Double> teamOdds) {
		try {
			teamBets.remove(teamBets.size()-1);
			teamOdds.remove(teamOdds.size()-1);
		}catch(Exception e) {}
	}
		
}
