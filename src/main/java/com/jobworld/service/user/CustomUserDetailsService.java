package com.jobworld.service.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jobworld.dao.user.UserDao;
import com.jobworld.entites.UserRole;
import com.jobworld.security.userdetails.CustomUserDetails;

public class CustomUserDetailsService implements UserDetailsService {

	private UserDao userDao;

	public CustomUserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		com.jobworld.entites.User user = userDao.findByUserName(arg0);
		Set<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		CustomUserDetails customUserDetails = new CustomUserDetails();

		customUserDetails.setUser(buildUserForAuthentication(user, authorities));
		customUserDetails.setAuthorities(authorities);
		customUserDetails.setUserId(user.getUserId());
		
		return customUserDetails;
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	@SuppressWarnings("unused")
	private User buildUserForAuthentication(com.jobworld.entites.User user,
			Set<GrantedAuthority> authorities) {
		return new User(user.getUserName(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

	private Set<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		Set<GrantedAuthority> Result = new HashSet<GrantedAuthority>(
				setAuths);

		return Result;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
