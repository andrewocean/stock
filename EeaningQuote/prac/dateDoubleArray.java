package prac;

public class dateDoubleArray {
	private String date;
	private double[] numArray;
	public dateDoubleArray(String date1,double[] numArray1){
		this.date=date1;
		numArray=numArray1;
	}
	
	public String getDate(){
		return date;
	}
	
	public double[] getNumArray(){
		return numArray;
	}
}
