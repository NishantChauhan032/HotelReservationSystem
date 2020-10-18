package com.capg.JUnit.hotelReservation;

public class Hotel {
	private String hotelName;
	private int regularCustomerWeekDayPrice;
	private int regularCustomerWeekEndPrice;
	
	
	public Hotel(String name, int regularCustomerWeekDayPrice, int regularCustomerWeekEndPrice) {
		super();
		this.hotelName = name;
		this.regularCustomerWeekDayPrice = regularCustomerWeekDayPrice;
		this.regularCustomerWeekEndPrice = regularCustomerWeekEndPrice;
	}

    
	public String getName() {
		return hotelName;
	}


	public void setName(String hotelName) {
		this.hotelName = hotelName;
	}


	public int getRegularCustomerWeekDayPrice() {
		return regularCustomerWeekDayPrice;
	}


	public void setRegularCustomerWeekDayPrice(int regularCustomerWeekDayPrice) {
		this.regularCustomerWeekDayPrice = regularCustomerWeekDayPrice;
	}


	public int getRegularCustomerWeekEndPrice() {
		return regularCustomerWeekEndPrice;
	}


	public void setRegularCustomerWeekEndPrice(int regularCustomerWeekEndPrice) {
		this.regularCustomerWeekEndPrice = regularCustomerWeekEndPrice;
	}


	@Override
	public String toString(){
		return "Hotel Name: "+ hotelName + "\n Regular weekday price : "+ regularCustomerWeekDayPrice
		       + "\nRegular weekend price : "+ regularCustomerWeekEndPrice + "\n";
	}

}
