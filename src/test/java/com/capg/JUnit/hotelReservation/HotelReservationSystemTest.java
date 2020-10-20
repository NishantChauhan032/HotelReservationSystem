package com.capg.JUnit.hotelReservation;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class HotelReservationSystemTest {

	@Test
	public void givenDetailsOfHotels_whenAddedToList_shouldShowSizeOfListAs3() {
		HotelReservationSystem hotelObject = new HotelReservationSystem();

		Hotel lakeWood = new Hotel("Lakewood", 110, 90);
		Hotel bridgeWood = new Hotel("Bridgewood", 160, 50);
		Hotel ridgeWood = new Hotel("Ridgewood", 220, 150);

		ArrayList<Hotel> hotelList1 = hotelObject.getHotelList();
		hotelList1.add(lakeWood);
		hotelList1.add(bridgeWood);
		hotelList1.add(ridgeWood);
		int listSize = hotelList1.size();
		
		Assert.assertEquals(3, listSize); //Test Case For Use Case 1

		Assert.assertEquals("Lakewood", HotelReservationSystem.findCheapestHotel()); // Test Case For Use case 2
	}

}
