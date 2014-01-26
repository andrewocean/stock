package prac;

import java.util.ArrayList;

public class arrayListPair {
	private ArrayList<String[]> dateQ ;
	private ArrayList<double[]> priceR ;
	arrayListPair(ArrayList<String[]> dateQ1,ArrayList<double[]> priceR1){
		this.dateQ=dateQ1;
		this.priceR=priceR1;
	}
	public ArrayList<String[]> getDateQ() {
		return dateQ;
	}
	
	public ArrayList<double[]> getPriceR() {
		return priceR;
	}
}
