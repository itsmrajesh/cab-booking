package com.cab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Booking {
	
	private Route route;
	private Cabdriver cab;

}
