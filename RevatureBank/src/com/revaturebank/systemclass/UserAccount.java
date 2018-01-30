package com.revaturebank.systemclass;

import org.apache.log4j.Logger;

// this is a class for the clients that granted an account with the revatureBank.
public class UserAccount extends Person{
   final static Logger logger =Logger.getLogger(UserAccount.class);
   private String accountNumber;
   private String username;
   private String password;
   private Double balanceAccount;
   private boolean isActrive;
   
   public UserAccount() {
	   
   }
    
   public UserAccount(String accountNumber, String username, String password, Double balanceAccount) {
	super();
	
	this.username = username;
	this.password = password;
	this.balanceAccount = balanceAccount;
	this.isActrive=false;
}
public UserAccount(String ssNumber, String firsName, String lastName, String phone, String email,
     String username, String password, Double balanceAccount) {
	super(ssNumber, firsName, lastName, phone, email);

	this.username = username;
	this.password = password;
	this.balanceAccount = balanceAccount;
	this.isActrive=false;
}

public boolean isActrive() {
	return isActrive;
}

public void setActrive(boolean isActrive) {
	this.isActrive = isActrive;
}

public String getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Double getBalanceAccount() {
	return balanceAccount;
}
public void setBalanceAccount(Double balanceAccount) {
	this.balanceAccount = balanceAccount;
}
//add fund to the account
public void deposit(double amount) {
	   this.balanceAccount+=amount;
	   System.out.println("your deposit succefully done");
   }
//withdraw fund from the account if there is any.
   public void withdraw(double cash) {
	   if (this.balanceAccount>=cash) {
	   this.balanceAccount-=cash;
	   System.out.println("$"+cash+" withdrawn ");
	   }
	   else {
		   System.out.println("Request denied: 'there is no enough fund in your account' ");
		   logger.warn("an attemps to withdraw under coverage");
	   }
   }
	   
   public UserAccount authentication(String uName,String pWord){
       return this;
       
   }
   
}
