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

	public void addHotel(String name, int weekdayPrice, int weekendPrice) {
		Hotel newHotel = new Hotel(name, weekdayPrice, weekendPrice);
		hotelList.add(newHotel);
	}


	public int findCheapestHotel() {

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

		Map<String,Integer> costOfHotel=new HashMap<String,Integer>();
		for(Hotel h:hotelList) {
			costOfHotel.put(h.getName(),calculateTotalAmount(h));
		}
		int lowestPrice=Collections.min(costOfHotel.values());
		System.out.println("Cheapest Hotel for the given dates is : ");
		costOfHotel.forEach((k,v)->{
			if(v==lowestPrice) {System.out.println(k+", Total Rates: $"+v);}
		});
		return lowestPrice;
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

	public static void main(String[] args) {
		System.out.println("Welcome to Hotel Reservation System!");
	}
}
