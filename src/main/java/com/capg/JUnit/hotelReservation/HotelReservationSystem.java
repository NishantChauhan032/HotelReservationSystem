package com.capg.JUnit.hotelReservation;

import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {
	private static ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	private static Scanner sc = new Scanner(System.in);

	public ArrayList<Hotel> getHotelList() {
		return hotelList;
	}

	public static void addHotel() {
		System.out.println("Enter Name Of the Hotel : ");
		String name = sc.nextLine();
		System.out.println("Enter the Price : ");
		int price = sc.nextInt();

		Hotel newHotel = new Hotel(name, price);
		hotelList.add(newHotel);
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Hotel Reservation System!");
		Hotel lakeWood = new Hotel("Lakewood", 110);
		Hotel bridgeWood = new Hotel("Bridgewood", 160);
		Hotel ridgeWood = new Hotel("Ridgewood", 220);

		hotelList.add(lakeWood);
		hotelList.add(bridgeWood);
		hotelList.add(ridgeWood);

		System.out.println(hotelList);
	}
}
