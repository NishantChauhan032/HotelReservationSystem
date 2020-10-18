package com.capg.JUnit.hotelReservation;

import java.util.ArrayList;

public class HotelReservationSystem {
	static ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

	public static void addHotel(String hotelName, int regularWeekDatPrice, int regularWeekEndPrice) {
		hotelList.add(new Hotel(hotelName, regularWeekDatPrice, regularWeekEndPrice));
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Hotel Reservation System!");
	}
}
