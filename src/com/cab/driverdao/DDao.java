package com.cab.driverdao;

import java.util.List;

import com.cab.domain.Cabdriver;
import com.cab.domain.Ride;

public interface DDao {
	
	Cabdriver login(String email,String dlid);
	
	void assignDriver(String dlid,String status);
	
	Ride getOngoingRide(String dlid);
	
	boolean endRide(String dlid);
	
	List<Ride> getAllRides(String dlid);

}
