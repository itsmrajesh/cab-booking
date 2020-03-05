package com.cab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Route {
	
	private String route;
	private int kms;
	private double fair = kms*2.5;
	

}
