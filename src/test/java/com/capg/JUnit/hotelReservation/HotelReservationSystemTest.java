package com.capg.JUnit.hotelReservation;

import org.junit.Assert;
import org.junit.Test;

public class HotelReservationSystemTest {

	@Test
	public void givenDetailsOfHotels_whenAddedToList_shouldShowSizeOfListAs3() {
		HotelReservationSystem hotelObject = new HotelReservationSystem();

		hotelObject.addHotel("Lakewood", 110, 90);
		hotelObject.addHotel("Lakewood", 160, 50);
		hotelObject.addHotel("Lakewood", 220, 150);
		int listSize = hotelObject.hotelList.size();
		Assert.assertEquals(3, listSize); // Test Case For Use Case 1
	}

	@Test
	public void givenDetailsOfThreeHotels_whenEnteredCorrect_shouldReturnCheapestHotel() {
		HotelReservationSystem hotelObject = new HotelReservationSystem();

		hotelObject.addHotel("Lakewood", 110, 90);
		hotelObject.addHotel("Lakewood", 160, 50);
		hotelObject.addHotel("Lakewood", 220, 150);

		Assert.assertEquals("Lakewood", hotelObject.findCheapestHotel());// Test Case For Use Case 2
	}

}
