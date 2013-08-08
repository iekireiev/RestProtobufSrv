package com.epam.ievgen_kireiev.proto_rest.dao;

import com.epam.ievgen_kireiev.proto_rest.entity.User;

public interface UserDao {

	public void addUser(User u);
	
	public User getUserByLogin(String login);
}
