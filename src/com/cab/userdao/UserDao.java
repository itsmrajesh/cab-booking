package com.cab.userdao;

import java.util.List;

import com.cab.domain.Booking;
import com.cab.domain.Ride;

public interface UserDao {
	
	boolean canRide(String email);
	
	Booking getRideDetails(String route);
	
	boolean confirmBooking(Booking booking,String email);
	
	List<Ride> getUserRides(String email);

}
