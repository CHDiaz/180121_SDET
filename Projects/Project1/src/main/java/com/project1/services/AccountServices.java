package com.project1.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.project1.beans.Account;
import com.project1.dao.TRMSDaoImpl;

public class AccountServices {
	static TRMSDaoImpl dao;
	Account account;
	
	public static boolean uniqueEmail(String email) {
		dao = new TRMSDaoImpl();
		List<String> emails = dao.getAllEmails();
		
		for (String e : emails) {
			if (email.equals(e)) return false;
		}
		return true;
	}
	
	public static void insertNewAccount(String firstname, String lastname, String address, String accountType, 
			String email, String password, String confirm) {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM.yyyy");
        String date = sdf.format(cal.getTime());
		dao = new TRMSDaoImpl();
		dao.insertIntoPersonal(email, firstname, lastname, address, date);
		if (accountType.equals("0")) dao.insertIntoAccounts(email, password, "0", "0", "0");
		else if (accountType.equals("1")) dao.insertIntoAccounts(email, password, "0", "1", "0");
		else dao.insertIntoAccounts(email, password, "0", "0", "1");
	}
	
	public static boolean validate(String email, String password){
		dao = new TRMSDaoImpl();
		String pw = dao.getStringValue(email, "pw", "account_info");
		if (pw == null) return false;
		
		if(pw.equals(password)){
			System.out.println("Successful login");
			return true;
		}else{
			System.out.println("Sorry dawgz");
			return false;
		}
	}
	
	public static String getName(String email) {
		dao = new TRMSDaoImpl();
		return dao.getStringValue(email, "first_name", "personal_info");
	}
	
	public static String getDate(String email) {
		dao = new TRMSDaoImpl();
		return dao.getStringValue(email, "join_date", "personal_info");
	}
	
	public static String getFullName(String email) {
		dao = new TRMSDaoImpl();
		String first = dao.getStringValue(email, "first_name", "personal_info");
		String last = dao.getStringValue(email, "last_name", "personal_info");
		return first + " " + last;
	}
	
	public static double getReimbursements(String email, String column) {
		dao = new TRMSDaoImpl();
		return dao.getDoubleValue(email, "reimbursements", column);
	}
	
	public static String accountStatus(String email) {
		dao = new TRMSDaoImpl();
		Account account = dao.selectAccountByEmail(email);
		if (account.getIsBenCo().equals("1")) return "Benefits Coordinator";
		if (account.getIsDepHead().equals("1")) return "Department";
		if (account.getIsDirSup().equals("1")) return "Direct Supervisor";
		return "Regular Account";
	}
	
	public static void approve(String email) {
		dao = new TRMSDaoImpl();
		dao.updateStringColumn(email, "account_info", "approved", "1");
	}
	
	public static boolean approved(String email) {
		dao = new TRMSDaoImpl();
		String approved = dao.getStringValue(email, "approved", "account_info");
		if (approved.equals("1")) return true;
		else return false;
	}
	
	public static boolean specialAccount(String email) {
		dao = new TRMSDaoImpl();
		Account account = dao.selectAccountByEmail(email);
		if (account.getIsBenCo().equals("1")) return true;
		if (account.getIsDepHead().equals("1")) return true;
		if (account.getIsDirSup().equals("1")) return true;
		return false;
	}
}
