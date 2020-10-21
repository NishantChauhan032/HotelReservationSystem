package com.capg.JUnit.hotelReservation;

public class Hotel {
	private String name;
	private int weekdayPrice;
	private int weekendPrice;
	private int rating;

	public Hotel(String name, int weekdayPrice, int weekendPrice, int rating) {
		super();
		this.name = name;
		this.weekdayPrice = weekdayPrice;
		this.weekendPrice = weekendPrice;
		this.rating = rating;
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "\nHotel Name : " + name + ", Weekday Price : $" + weekdayPrice + ", Weekend Price : $" + weekendPrice
				+ ", Rating : " + rating;
	}

}
