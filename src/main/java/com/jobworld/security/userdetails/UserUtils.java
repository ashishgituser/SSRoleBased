package com.jobworld.security.userdetails;

import org.springframework.security.core.context.SecurityContextHolder;

import com.jobworld.entites.User;

public class UserUtils {
	
	private static CustomUserDetails activeUser;
	private static User user;

	public static User getUser() {
		activeUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		user = new User();
		user.setUserId(activeUser.getUserId());
		return user;
	}
}
