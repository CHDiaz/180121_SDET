package com.revaturebank.systemclass;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class AdminList implements Exportable{
	//logger of this class
	final static Logger logger=Logger.getLogger(UserAccountList.class);
	private HashMap <String,Admin>  adminList;
	
	public AdminList() {
		this.adminList=new HashMap<>();
	}
	
	public Admin authenticate(String username,String password) throws Exception{
		try{    
			   dataLoad();
			   return adminList.get(username);
			   }
			catch (Exception e){
			    throw new Exception(e );
			    }

			}
	// add an admin to the system
    public boolean add(Admin a) {
    	return adminList.putIfAbsent(a.getUsername(), a) == null;
		}
    
	public void clear() {
		 try{ adminList.clear();  
		     }
		 catch (Exception e){
		    e.printStackTrace();
		   }
	}
	@Override
	public void dataLoad() {
		try{ BufferedReader rb = new BufferedReader(new FileReader("src\\com\\revaturebank\\data\\AdminData.txt"));
        String line;
        line=rb.readLine();
        while(line!=null){
           String [] arrline=line.split(",");
           Admin a= new Admin();
           a.setSsNumber(arrline[0]);
           a.setFirsName(arrline[1]);
           a.setLastName(arrline[2]);
           a.setPhone(arrline[3]);
           a.setEmail(arrline[4]);
           a.setAdminId(arrline[5]);
           a.setUsername(arrline[6]);
           a.setPassword(arrline[7]);
           
           try {
			this.add(a);
		    } 
           catch (Exception e) {
			e.printStackTrace();
		    }
            line=rb.readLine();
          }
          rb.close();
       }
       catch (IOException ex){
        try{PrintWriter out = new PrintWriter("src\\com\\revaturebank\\data\\AdminData.txt");
          out.close();
          logger.error("New file just creates there is no adim data");
       } catch (FileNotFoundException ex1){
          ex1.getStackTrace();
         }
      
         }
		
		
	}

	@Override
	public void dataSave() {
		try {
	        PrintWriter output;
	        output = new PrintWriter( new BufferedWriter( new FileWriter("src\\com\\revaturebank\\data\\AdminData.txt")));
	        adminList.entrySet().stream().forEach((admin) -> {
	            output.println(adminList.get(admin.getKey()).getSsNumber()+","+adminList.get(admin.getKey()).getFirsName()+
	            		","+adminList.get(admin.getKey()).getLastName()+","+adminList.get(admin.getKey()).getPhone()+","
	            		+adminList.get(admin.getKey()).getEmail()+","+adminList.get(admin.getKey()).getAdminId()+","
	            		+adminList.get(admin.getKey()).getUsername()+","+adminList.get(admin.getKey()).getPassword());
	        });
	        output.close();
	    } catch (IOException ex) {
	    	logger.error("IOS proble in saving admin data ");
	        ex.getStackTrace();
	    }
		
	}
	
}
