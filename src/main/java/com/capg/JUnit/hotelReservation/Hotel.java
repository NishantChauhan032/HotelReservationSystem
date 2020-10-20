package com.capg.JUnit.hotelReservation;

public class Hotel {
	private String name;
	private int price;

	public Hotel(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "\nHotel Name : " + name + ", Price(per day) : $" + price;
	}

}
