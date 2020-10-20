package com.capg.JUnit.hotelReservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class HotelReservationSystem {
	private static ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	private static Scanner sc = new Scanner(System.in);
	private static Date checkInDate, checkOutDate;

	public ArrayList<Hotel> getHotelList() {
		return hotelList;
	}

	public static void addHotel() {
		System.out.println("Enter Name Of the Hotel : ");
		String name = sc.nextLine();
		System.out.println("Enter Weekday price of the Hotel : ");
		int weekdayPrice = Integer.parseInt(sc.nextLine());
		System.out.println("Enter Weekend price of the Hotel : ");
		int weekendPrice = Integer.parseInt(sc.nextLine());

		Hotel newHotel = new Hotel(name, weekdayPrice, weekendPrice);
		hotelList.add(newHotel);
	}

	public static String findCheapestHotel() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
		System.out.println("Enter Check-In date(ddMMMyyyy),Check-Out date(ddMMMyyyy):");
		String dateInput = sc.next();

		String[] dates = dateInput.split(","); // removes , and store dates in an array
		try {
			checkInDate = dateFormat.parse(dates[0]); //putting check in date at 0th index
		} catch (ParseException e) {
			System.out.println("Please Enter a Valid CheckIn Date!");
		}
		try {
			checkOutDate = dateFormat.parse(dates[1]); //putting checkout date at 1st index
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
		Hotel lakeWood = new Hotel("Lakewood", 110, 90);
		Hotel bridgeWood = new Hotel("Bridgewood", 160, 50);
		Hotel ridgeWood = new Hotel("Ridgewood", 220, 150);

		hotelList.add(lakeWood);
		hotelList.add(bridgeWood);
		hotelList.add(ridgeWood);

		System.out.println(hotelList);
		findCheapestHotel();
	}
}
