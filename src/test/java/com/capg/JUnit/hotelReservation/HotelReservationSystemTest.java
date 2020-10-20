package com.capg.JUnit.hotelReservation;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class HotelReservationSystemTest {

	@Test
	public void givenDetailsOfHotels_whenAddedToList_shouldShowSizeOfListAs3() {
		HotelReservationSystem hotelObject = new HotelReservationSystem();

		hotelObject.addHotel("Lakewood", 110, 90 , 3);
		hotelObject.addHotel("Bridgewood", 150, 50 , 4);
		hotelObject.addHotel("Ridgewood", 220, 150 , 5);
		
		int listSize = hotelObject.hotelList.size();
		Assert.assertEquals(3, listSize); // Test Case For Use Case 1
	}

	@Test
	public void givenDetailsOfThreeHotels_whenEnteredCorrect_shouldReturnCheapestHotel() {
		HotelReservationSystem hotelObject = new HotelReservationSystem();

		hotelObject.addHotel("Lakewood", 110, 90 , 3);
		hotelObject.addHotel("Bridgewood", 150, 50 , 4);
		hotelObject.addHotel("Ridgewood", 220, 150 , 5);

		Hotel hotel = hotelObject.findBestRatedHotelFromTheCheapestHotels();
		Assert.assertEquals(hotel.getName(),"Bridgewood");

	}
	
	@Test
	public void givenDetailsOfThreeHotels_whenEnteredCorrect_shouldReturnBestRatedHotel() {
		HotelReservationSystem hotelObject = new HotelReservationSystem();

		hotelObject.addHotel("Lakewood", 110, 90 , 3);
		hotelObject.addHotel("Bridgewood", 150, 50 , 4);
		hotelObject.addHotel("Ridgewood", 220, 150 , 5);

		Map<Hotel,Integer> value =hotelObject.findBestRatedHotelOfAll();
		Assert.assertEquals(value.keySet().stream().findFirst().get().getName(),"Ridgewood");

	}
	
}
