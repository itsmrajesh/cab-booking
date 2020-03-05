package com.cab.driverdao;

import com.cab.domain.Cabdriver;

public interface DDao {
	
	Cabdriver login(String email,String dlid);

}
