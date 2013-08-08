package com.epam.ievgen_kireiev.proto_rest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.ievgen_kireiev.proto_rest.entity.User;
import org.hibernate.criterion.Restrictions;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}


	public User getUserByLogin(String login) {
		List list = sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("login", login)).list();
	return (User)list.get(0);
	}

}
