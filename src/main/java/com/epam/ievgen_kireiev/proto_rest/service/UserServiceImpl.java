package com.epam.ievgen_kireiev.proto_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.ievgen_kireiev.proto_rest.dao.UserDao;
import com.epam.ievgen_kireiev.proto_rest.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	
	public void addUser(User user) {
		userDao.addUser(user);
	}


	public User getUserByLogin(String login) {
		return userDao.getUserByLogin(login);
	}

}
