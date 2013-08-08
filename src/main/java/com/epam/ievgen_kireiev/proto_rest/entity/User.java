package com.epam.ievgen_kireiev.proto_rest.entity;


import javax.persistence.*;



@Entity
@Table(name = "AUTH_USER")
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUTH_ID", unique=true, nullable=false)
	private int id;
	
	@Column(name = "AUTH_LOGIN", unique=true, nullable=false)
	private String login;
	
	@Column(name = "AUTH_PASSWORD", nullable=false)
	private String password;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return ("User: [id " +id + "] login "+login);
		
	}

}
