package com.capg.JUnit.hotelReservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class HotelReservationSystem {
	public ArrayList<Hotel> hotelList;
	private static Scanner sc = new Scanner(System.in);
	public Date checkInDate, checkOutDate;

	public HotelReservationSystem() {
		hotelList = new ArrayList<Hotel>();
	}

	public void addHotel(String name, int weekdayPrice, int weekendPrice) {
		Hotel newHotel = new Hotel(name, weekdayPrice, weekendPrice);
		hotelList.add(newHotel);
	}

	public String findCheapestHotel() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
		System.out.println("Enter Check-In date(ddMMMyyyy),Check-Out date(ddMMMyyyy):");
		String dateInput = sc.next();

		String[] dates = dateInput.split(","); // removes , and store dates in an array
		try {
			checkInDate = dateFormat.parse(dates[0]); // putting check in date at 0th index
		} catch (ParseException e) {
			System.out.println("Please Enter a Valid CheckIn Date!");
		}
		try {
			checkOutDate = dateFormat.parse(dates[1]); // putting checkout date at 1st index
		} catch (ParseException e) {
			System.out.println("Please Enter A Valid CheckOut Date!");
		}

		long dateDifference = checkOutDate.getTime() - checkInDate.getTime();
		int daysToBook = (int) (dateDifference / (1000 * 60 * 60 * 24)) + 1; // converting milliSecond to days

		Hotel cheapestHotel = hotelList.get(0);
		for (Hotel h : hotelList) {
			if (h.getWeekdayPrice() < cheapestHotel.getWeekdayPrice()) {
				cheapestHotel = h;
			}
		}
		int price = cheapestHotel.getWeekdayPrice();
		String hotelName = cheapestHotel.getName();
		int sumTotal = (int) daysToBook * price;
		System.out.println("Cheapest Hotel for the given date is - " + hotelName + "\nTotal Price : $" + sumTotal);
		return hotelName;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Hotel Reservation System!");
	}
}
