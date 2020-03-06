package com.cab.dao;

import java.util.List;

import com.cab.domain.Cabdriver;
import com.cab.domain.Ride;
import com.cab.domain.Route;
import com.cab.domain.User;

public interface Dao {

	boolean userSignup(User user);

	User userLogin(String email, String password);
	
	List<User> getAllUsers();

	boolean driverSignup(Cabdriver cab);
	
	List<Cabdriver> getAllCabDrivers();
	
	Cabdriver getCabDriver(String dlid);
	
	boolean updateStatus(String dlid,String status);
	
	boolean addNewRoute(Route route);
	
	Route getRoute(String route);
	
	List<Route> getAllRoutes();
	
	boolean assignRoute(Route route,String myRoute);
	
	List<Ride> getAllRides();
	
	
}
