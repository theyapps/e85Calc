package com.theyapps.e85calc;

public class MileageObject 
{
	private int price; // stored in count of pennies
	private double mileage;
	
	/**
	 * 
	 * @param price - entered in pennies
	 * @param mileage - entered in dist/volume
	 */
	public MileageObject(int price, double mileage) 
	{
		this.price = price;
		this.mileage = mileage;
	}
	
}
