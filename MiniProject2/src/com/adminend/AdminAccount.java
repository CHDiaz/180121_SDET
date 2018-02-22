package com.adminend;


/**
 * 
 * Class contains all  Admin info and setters/ getters for the Admin infos,
 * there is onoly one admin and he is already in the datatbase, in order to access
 * the admin account you just need to type in the following password
 * Admin Password is ADMIN12345 
 * 
 *
 */
 
public class AdminAccount {

	private String Name;
	private Integer Admin_ID;
	private String UserName;
	private String Password;

	public AdminAccount(Integer admin_id, String name, String username, String pw) {

		super();
		Admin_ID = admin_id;
		Name = name;
		UserName = username;
		Password = pw;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Integer getAdmin_ID() {
		return Admin_ID;
	}

	public void setAdmin_ID(Integer admin_ID) {
		Admin_ID = admin_ID;
	}

}