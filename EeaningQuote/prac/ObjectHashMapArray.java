package prac;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectHashMapArray {
	private HashMap<String, Integer> dateArray = null;
	private ArrayList<dateDoubleArray> stockData = null;

	public void setData(HashMap<String, Integer> dateArray1,
			ArrayList<dateDoubleArray> stockData1) {
		this.dateArray = dateArray1;
		this.stockData = stockData1;
	}

	public HashMap<String, Integer> getHash() {
		return this.dateArray;
	}

	public ArrayList<dateDoubleArray> getArray() {
		return this.stockData;
	}
}
