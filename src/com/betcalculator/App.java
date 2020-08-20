package com.betcalculator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.betcalculator.frontend.FrontEndFX;

import javafx.application.Application;

public class App implements Runnable {
	
	
	public static Thread thread;
	public static WebDriver driver; 
	public static String matchURL = "https://sports.betway.com/pt/sports/evt/6263254";
	public static double oddTeamOne, oddTeamTwo;
	public static BackendCalcs backendCalcs;
	
	
	public static String nameTeamOne = "TeamOne", nameTeamTwo = "TeamTwo";
	
	public App() {
		
		backendCalcs = new BackendCalcs();
		startDriver();
	}
		
	public static void main(String[] args) throws Exception {
			App app = new App();
			app.startThread();
			Application.launch(FrontEndFX.class, args);
	}
	
	public static void getActualOdds() {
		System.out.println(nameTeamOne + " " +  oddTeamOne + " // " + nameTeamTwo + " " + oddTeamTwo);
	}
	
	public static void scrapBetway() {	
		Document doc;
		Element element;
		
		try {
			doc =  Jsoup.parse(driver.getPageSource());
			element = doc.getElementsByClass("standardMarket").get(0);
			String odds = element.getElementsByClass("oddsDisplay").text().replaceAll(",", ".");
			String[] oddsArray = odds.split(" ");
			
			oddTeamOne = Double.valueOf(oddsArray[0]); 
			oddTeamTwo = Double.valueOf(oddsArray[1]); 
			
			String[] namesArray =  new String[2];
			namesArray[0] = element.getElementsByClass("outcomeHeader").first().text();
			namesArray[1] = element.getElementsByClass("outcomeHeader").last().text();
			
			nameTeamOne = namesArray[0]; 
			nameTeamTwo = namesArray[1];
			
		}catch(Exception e) {
			//System.out.println("null");
		}
	}
	
	public void startThread() {
		thread = new Thread(this);
		thread.start();	
	}

	public static void startDriver() {
		System.out.println("Starting browser...");
		ChromeOptions options = new ChromeOptions();
    	options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");    
    	driver = new ChromeDriver(options);

	}
	
	public static void closeBrowser() {
		try {
			driver.quit();
		}catch(NullPointerException e) {
			System.out.println("The browser was already closed.");
		}
		
	}
	
	@Override
	public void run(){
		  long lastTime = System.nanoTime();
	        double amountOfTicks = 1;
	        double ns = 1000000000 / amountOfTicks;
	        double delta = 0;
	        //int frames = 0;
	        double timer = System.currentTimeMillis();
	        
	        while(true) {
	            long now = System.nanoTime();
	            delta = delta + ((now - lastTime) / ns);
	            lastTime = now;
	            if(delta >= 1) {
	            	//call functions
	            	scrapBetway();
	            	backendCalcs.equalValue();
	            	//getActualOdds();
	                //frames++;
	                delta--;
	            }
	            
	            if(System.currentTimeMillis() - timer >= 1000) {
	                //System.out.println(frames);
	                //frames = 0;
	                timer = timer + 1000;
	            }    
	        }
	}
	
	
}
