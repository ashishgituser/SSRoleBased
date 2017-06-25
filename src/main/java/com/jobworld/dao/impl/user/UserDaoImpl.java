package com.jobworld.dao.impl.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.jobworld.dao.user.UserDao;
import com.jobworld.entites.User;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		List<User> users = new ArrayList<User>();

		System.out.println("User Name :::::::::::::>>>>>> " + username);
		
		users = getSessionFactory().openSession()
			.createQuery("from User where userName=?")
			.setParameter(0, username).list();

		if (users.size() > 0) {
			System.out.println("Got the results ::::::::>>>>>>>>>>>>>>>>>>>>>>>>>>");
			return users.get(0);
		} else {
			System.out.println("Not Got the results ::::::::>>>>>>>>>>>>>>>>>>>>>>>>>>");
			return null;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
