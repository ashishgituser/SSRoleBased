package com.jobworld.dao.user;

import com.jobworld.entites.User;

public interface UserDao {
	User findByUserName(String username);
}
