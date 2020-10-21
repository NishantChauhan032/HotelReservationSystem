package com.capg.JUnit.hotelReservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class HotelReservationSystem {
	public ArrayList<Hotel> hotelList;
	private static Scanner sc = new Scanner(System.in);
	public Date checkInDate, checkOutDate;

	public HotelReservationSystem() {
		hotelList = new ArrayList<Hotel>();
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Hotel Reservation System!");
		HotelReservationSystem temp = new HotelReservationSystem();
		try {
			temp.findBestRatedHotelOfAll();
		} catch (InvalidInputException e) {
			System.out.println(e);
		}
	}

	public void addHotel(String name, int weekdayPrice, int weekendPrice, int rating) {
		Hotel newHotel = new Hotel(name, weekdayPrice, weekendPrice, rating);
		hotelList.add(newHotel);
	}

	public void setDates() throws InvalidInputException { //date validation using Regex

		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
		System.out.println("Please specify Check-In date(ddMMMyyyy),Check-Out date(ddMMMyyyy):");
		String temp = sc.nextLine();
		String[] dates = temp.split(",");
		Pattern pattern = Pattern.compile("[0-9]{2}[A-Z][a-z]{2}[0-9]{4}");
		Matcher matcher1 = pattern.matcher(dates[0]);
		Matcher matcher2 = pattern.matcher(dates[1]);
		if (matcher1.matches()) {
			try {
				checkInDate = dateFormat.parse(dates[0]);
			} catch (ParseException e) {
				System.out.println("Cannot Parse Checkin Date");
			}
		} else {
			throw new InvalidInputException("Invalid Checkin Date");
		}
		if (matcher2.matches()) {
			try {
				checkOutDate = dateFormat.parse(dates[1]);
			} catch (ParseException e) {
				System.out.println("Cannot Parse Checkout Date");
			}
		} else {
			throw new InvalidInputException("Invalid Checkout Date");
		}
	}

	public Map<Hotel, Integer> findCheapestHotel() throws InvalidInputException { 
		getPriceAsPerTypeOfCustomer();
		System.out.println(hotelList);
		setDates();

		Map<Hotel, Integer> costOfHotel = new HashMap<Hotel, Integer>();
		Map<Hotel, Integer> cheapestHotelList = new HashMap<Hotel, Integer>();

		costOfHotel = hotelList.stream().collect(Collectors.toMap(h -> h, h -> calculateTotalAmount(h)));
		int lowestPrice = Collections.min(costOfHotel.values());
		System.out.println("Cheapest Hotel for the given dates is : ");
		costOfHotel.forEach((k, v) -> {
			if (v == lowestPrice) {
				cheapestHotelList.put(k, v);
				System.out.println(k.getName() + ", Total Rates: $" + v);
			}
		});
		return cheapestHotelList;
	}

	public ArrayList<Hotel> findBestRatedHotelFromTheCheapestHotels() throws InvalidInputException {
		Map<Hotel, Integer> cheapestHotelList = findCheapestHotel();
		ArrayList<Integer> ratingList = new ArrayList<Integer>();
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		cheapestHotelList.forEach((k, v) -> {
			ratingList.add(k.getRating());
		});
		int maxRating = Collections.max(ratingList);
		cheapestHotelList.forEach((k, v) -> {
			if (k.getRating() == maxRating) {
				list.add(k);
			}
		});
		list.stream().forEach(k -> System.out.println(
				"Cheapest High Rated Hotel is: \n" + k.getName() + ", Total Rates: $" + calculateTotalAmount(k)));
		return list;
	}

	public Map<Hotel, Integer> findBestRatedHotelOfAll() throws InvalidInputException {
		getPriceAsPerTypeOfCustomer();
		System.out.println(hotelList);
		setDates();
		Map<Hotel, Integer> hotelRatingMap = new HashMap<Hotel, Integer>();
		Map<Hotel, Integer> highestRatedHotelMap = new HashMap<Hotel, Integer>();
		hotelRatingMap = hotelList.stream().collect(Collectors.toMap(h -> h, h -> h.getRating()));
		int maximumHotelRating = Collections.max(hotelRatingMap.values());
		hotelRatingMap.forEach((k, v) -> {
			if (v == maximumHotelRating) {
				highestRatedHotelMap.put(k, calculateTotalAmount(k));
			}
		});
		highestRatedHotelMap.entrySet().stream().forEach(k -> System.out
				.println("Highest Rated Hotel is: \n" + k.getKey().getName() + ", Total Rates: $" + k.getValue()));
		return highestRatedHotelMap;
	}

	public int calculateTotalAmount(Hotel h) {
		long difference = checkOutDate.getTime() - checkInDate.getTime();
		int noOfDays = (int) (difference / (1000 * 60 * 60 * 24)) + 1; // Convert milliseconds to days
		int weekdayPrice = h.getWeekdayPrice();
		int weekendPrice = h.getWeekendPrice();
		int numOfWeekends = getWeekendsCount(checkInDate, checkOutDate);

		int sumTotal = ((noOfDays - numOfWeekends) * weekdayPrice + numOfWeekends * weekendPrice);
		return sumTotal;
	}

	public static int getWeekendsCount(Date start, Date end) { //method to count weekends
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		int weekendsCount = 0;
		String day = "";
		String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday" };
		while (calStart.before(calEnd) || calStart.equals(calEnd)) {
			day = strDays[calStart.get(Calendar.DAY_OF_WEEK) - 1];
			if (day.equals("Sunday") || day.equals("Saturday")) {
				weekendsCount++;
			}
			calStart.add(Calendar.DAY_OF_MONTH, 1);
		}
		return weekendsCount;
	}

	public void getPriceAsPerTypeOfCustomer() {
		System.out.println("Please select the type of customer(1/2) : \n1- Regular Customer \n2- Reward Customer");
		int customerType = Integer.parseInt(sc.nextLine());
		if (customerType == 1) {
			addHotel("Lakewood", 110, 90, 3);
			addHotel("Bridgewood", 150, 50, 4);
			addHotel("Ridgewood", 220, 150, 5);
		} else if (customerType == 2) {
			addHotel("Lakewood", 80, 80, 3);
			addHotel("Bridgewood", 110, 50, 4);
			addHotel("Ridgewood", 100, 40, 5);
		} else {
			System.out.println("Invalid Input,please try again!");
			getPriceAsPerTypeOfCustomer();
		}
	}

}
