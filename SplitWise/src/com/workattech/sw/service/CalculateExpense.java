package com.workattech.sw.service;

public class CalculateExpense {
	public double CalculateMoneyEqual(int amount,int number)
	{
		return amount/number;
	}
	public double CalculateMoneyPercent(int amount,int percent)
	{
		double share=percent/100.0;
		return amount*share;
	}

}
