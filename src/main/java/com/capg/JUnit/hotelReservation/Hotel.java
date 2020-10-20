package com.capg.JUnit.hotelReservation;

public class Hotel {
	private String name;
	private int weekdayPrice;
	private int weekendPrice;

	public Hotel(String name, int weekdayPrice, int weekendPrice) {
		super();
		this.name = name;
		this.weekdayPrice = weekdayPrice;
		this.weekendPrice = weekendPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeekdayPrice() {
		return weekdayPrice;
	}

	public void setWeekdayPrice(int weekdayPrice) {
		this.weekdayPrice = weekdayPrice;
	}

	public int getWeekendPrice() {
		return weekendPrice;
	}

	public void setWeekendPrice(int weekendPrice) {
		this.weekendPrice = weekendPrice;
	}

	@Override
	public String toString() {
		return "\nHotel Name : " + name + ", Weekday Price : $" + weekdayPrice + ", Weekend Price : $" + weekendPrice;
	}

}
