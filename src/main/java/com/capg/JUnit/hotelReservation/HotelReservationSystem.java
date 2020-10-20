package com.capg.JUnit.hotelReservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelReservationSystem {
	public ArrayList<Hotel> hotelList;
	private static Scanner sc = new Scanner(System.in);
	public Date checkInDate, checkOutDate;

	public HotelReservationSystem() {
		hotelList = new ArrayList<Hotel>();
	}

	public void addHotel(String name, int weekdayPrice, int weekendPrice , int rating) {
		Hotel newHotel = new Hotel(name, weekdayPrice, weekendPrice , rating);
		hotelList.add(newHotel);
	}


	public Map<Hotel,Integer> findCheapestHotel() {

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

		Map<Hotel,Integer> costOfHotel=new HashMap<Hotel,Integer>();
		Map<Hotel,Integer> cheapestHotelList = new HashMap<Hotel,Integer>();
		
		for(Hotel h:hotelList) {
			costOfHotel.put(h,calculateTotalAmount(h));
		}
		int lowestPrice=Collections.min(costOfHotel.values());
		System.out.println("Cheapest Hotel for the given dates is : ");
		costOfHotel.forEach((k,v)->{
			if(v==lowestPrice) {
				cheapestHotelList.put(k, v);
				System.out.println(k.getName()+", Total Rates: $"+v);}
		});
		return cheapestHotelList;
	}
	
	public Hotel findBestRatedHotelFromTheCheapestHotels() {
		Map<Hotel,Integer>cheapestHotelList= findCheapestHotel();
		ArrayList<Integer> ratingList=new ArrayList<Integer>();
		ArrayList<Hotel> list=new ArrayList<Hotel>();
		cheapestHotelList.forEach((k,v)->{
			ratingList.add(k.getRating());
		});
		int maxRating=Collections.max(ratingList);
		cheapestHotelList.forEach((k,v)->{
			if(k.getRating()==maxRating) {
				list.add(k);
				System.out.println("Cheapest hotel with highest rating is: "+ k.getName()+" with Total Rate: $"+v);
			}
		});
		return list.get(0);
	}
	
	public Map<Hotel, Integer> findBestRatedHotelOfAll() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
		System.out.println("Enter Check-In date(ddMMMyyyy),Check-Out date(ddMMMyyyy) to find best rated hotel for you :");
		String inputDates = sc.next();
		String[] dates = inputDates.split(",");
		try {
			checkInDate = dateFormat.parse(dates[0]);
			checkOutDate = dateFormat.parse(dates[1]);
		} catch (ParseException e) {
			System.out.println("invalid check date format");
		}
		Map<Hotel, Integer> hotelratingList = new HashMap<Hotel, Integer>();
		Map<Hotel, Integer> highestRatedHotelMap = new HashMap<Hotel, Integer>();
		for (Hotel h : hotelList) {
			hotelratingList.put(h, h.getRating());
		}
		int maximumHotelRating = Collections.max(hotelratingList.values());
		hotelratingList.forEach((k, v) -> {
			if (v == maximumHotelRating) {
				highestRatedHotelMap.put(k, calculateTotalAmount(k));
				System.out.println(
						"\nHighest Rated Hotel is: " + k.getName() + ", Total Rate: $" + highestRatedHotelMap.get(k));
			}
		});
		return highestRatedHotelMap;
	}
	
	public int calculateTotalAmount(Hotel h) {
        long difference = checkOutDate.getTime() - checkInDate.getTime();
        int noOfDays = (int) (difference / (1000 * 60 * 60 * 24)) + 1; //Convert milliseconds to days
        int weekdayPrice = h.getWeekdayPrice();
        int weekendPrice = h.getWeekendPrice();
        int numOfWorkdays = getNumOfWorkdays(checkInDate, checkOutDate);
        
        int sumTotal = (numOfWorkdays * weekdayPrice +(noOfDays-numOfWorkdays)*weekendPrice);
        return sumTotal;
    }

    public int getNumOfWorkdays(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int workDays = 0;
       
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }

        do {
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

        return workDays;
    }
    
    public void getPriceAsPerTypeOfCustomer() {
		System.out.println("Please select the type of customer(1/2) : \n1- Regular Customer \n2- Reward Customer");
		int customerType=Integer.parseInt(sc.nextLine());
		if(customerType==1) {
			addHotel("Lakewood", 110, 90, 3);
			addHotel("Bridgewood", 150, 50, 4);
			addHotel("Ridgewood", 220, 150, 5);
		}
		if(customerType==2) {
			addHotel("Lakewood", 80, 80, 3);
			addHotel("Bridgewood", 110, 50, 4);
			addHotel("Ridgewood", 100, 40, 5);
		}
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Hotel Reservation System!");
	}
}
