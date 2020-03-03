package com.cab.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Cabdriver {

	private String name;
	private int age;
	private String email, phone, dlid, carname, vechileno, route, address, status="Pending";

}
