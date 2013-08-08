package com.epam.ievgen_kireiev.proto_rest.service;

import com.epam.ievgen_kireiev.proto_rest.entity.User;

public interface UserService {
	
	public void addUser(User u);

	public User getUserByLogin(String login);
}
