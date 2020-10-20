package com.capg.JUnit.hotelReservation;

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

		int value = hotelObject.findCheapestHotel();
		Assert.assertEquals(value,200);

	}

}
