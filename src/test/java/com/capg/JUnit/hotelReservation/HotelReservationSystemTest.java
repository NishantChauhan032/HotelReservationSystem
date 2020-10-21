package com.capg.JUnit.hotelReservation;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class HotelReservationSystemTest {

	@Test
	public void givenDetailsOfHotels_whenAddedToList_shouldShowSizeOfListAs3() {
		HotelReservationSystem hotelObject = new HotelReservationSystem();

		hotelObject.addHotel("Lakewood", 110, 90, 3);
		hotelObject.addHotel("Bridgewood", 150, 50, 4);
		hotelObject.addHotel("Ridgewood", 220, 150, 5);

		int listSize = hotelObject.hotelList.size();
		Assert.assertEquals(3, listSize); // Test Case For Use Case 1
	}

	@Test // for regular customer
	public void givendetailsOfHotelsForRegularCustomer_whenCorrect_shouldReturnTrue() throws InvalidInputException {

		HotelReservationSystem hotelObject = new HotelReservationSystem();
		ArrayList<Hotel> rate = hotelObject.findBestRatedHotelFromTheCheapestHotels();
		Assert.assertEquals(hotelObject.calculateTotalAmount(rate.get(0)), 200);
	}

	@Test // for reward customer
	public void givendetailsOfHotelsForRewardCustomer_whenCorrect_shouldReturnTrue() throws InvalidInputException {

		HotelReservationSystem hotelObject = new HotelReservationSystem();
		ArrayList<Hotel> rate = hotelObject.findBestRatedHotelFromTheCheapestHotels();
		Assert.assertEquals(hotelObject.calculateTotalAmount(rate.get(0)), 140);
	}
}
