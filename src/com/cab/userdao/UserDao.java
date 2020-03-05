package com.cab.userdao;

import com.cab.domain.Booking;

public interface UserDao {
	
	boolean canRide(String email);
	
	Booking getRideDetails(String route);

}
