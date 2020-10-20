package com.capg.JUnit.hotelReservation;

import org.junit.Test;

import java.util.ArrayList;

import org.junit.Assert;

public class HotelReservationSystemTest {
	@Test
	public void givenDetailsOfHotels_whenAddedToList_shouldShowSizeOfListAs3() {
		HotelReservationSystem hotelObject = new HotelReservationSystem();

		Hotel lakeWood = new Hotel("Lakewood", 110);
		Hotel bridgeWood = new Hotel("Bridgewood", 160);
		Hotel ridgeWood = new Hotel("Ridgewood", 220);

		ArrayList<Hotel> hotelList = hotelObject.getHotelList();
		hotelList.add(lakeWood);
		hotelList.add(bridgeWood);
		hotelList.add(ridgeWood);
		int listSize = hotelList.size();
		Assert.assertEquals(3, listSize);
	}
}
