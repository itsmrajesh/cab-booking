package com.cab.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Ride {

	private String email, dlid, route;
	private double fair;
	private String status;

}
