package com.cab.dao;

import java.util.List;

import com.cab.domain.Cabdriver;
import com.cab.domain.User;

public interface Dao {

	boolean userSignup(User user);

	User userLogin(String email, String password);

	boolean driverSignup(Cabdriver cab);
	
	List<Cabdriver> getAllCabDrivers();
}
