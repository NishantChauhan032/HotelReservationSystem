package com.capg.JUnit.hotelReservation;

import org.junit.Test;

import org.junit.Assert;

public class HotelReservationSystemTest 
{
    @Test
    public void givenDetailsOfHotels_whenAddedToList_shouldShowSizeOfListAs3() {
    	HotelReservationSystem.addHotel("Lakewood",110,90);
    	HotelReservationSystem.addHotel("BridgeWood",160,60);
    	HotelReservationSystem.addHotel("Ridgewood",220,150);
    	int size = HotelReservationSystem.hotelList.size();
    	
    	Assert.assertEquals(3,size);
    }
}
