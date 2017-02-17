package com.main.test;

import com.dto.UserDetailsDTO;

public class AppInfo {

	static private UserDetailsDTO userDetails;

	public static UserDetailsDTO getUserDetails() {
		return userDetails;
	}

	public static void setUserDetails(UserDetailsDTO userDetails) {
		AppInfo.userDetails = userDetails;
	}
	
}
