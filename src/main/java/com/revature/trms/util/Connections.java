package com.revature.trms.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Connections {
	public static Connection getConnection() throws SQLException{
		
		try {Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch(ClassNotFoundException e){
			e.getStackTrace();
		}
		
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				                            "trms",
				                            "trms");
	}

}
