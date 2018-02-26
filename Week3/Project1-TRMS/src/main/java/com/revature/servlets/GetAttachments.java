package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.revature.beans.Attachments;
import com.revature.service.DataService;
import com.revature.util.HTMLTemplates;

public class GetAttachments extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int grade_bit = 0;
		int rei_id = 0;
		String grade_received = "N/A";

		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			HTMLTemplates.headers(out);
			HTMLTemplates.navbar(out);
			out.println("No Files/No Valid files Detected. Please try again!<br/>");
			return;
		}

		FileItemFactory factory = new DiskFileItemFactory(); //Create A FileItemFactory object
		ServletFileUpload upload = new ServletFileUpload(factory); //Create a ServletFileUpload object
		
		try {
			List<FileItem> fields = upload.parseRequest(request); // Parse the request into a list of FileItems
			List<File> attachments = new ArrayList<File>();
			Iterator<FileItem> it = fields.iterator();			// Create an iterator for the list that was created above
			
			
			
			while(it.hasNext()) { // iterate through the list while there are still elements within the list of FileItems.
				FileItem file = (FileItem)it.next(); //Pull the next object in line in the list that the iterator is pointing too
					if(!file.isFormField()) {
						if(!file.getName().equals("")) {
						String fileName = file.getName(); // Acquire name of the file and save it in a string.
						File attachedFile = new File(fileName); // Create a new file with the name of the attachment file from the list.
						file.write(attachedFile); // Write the file that was in the list, into the newly created file with the files name.
						attachments.add(attachedFile);
						grade_bit = 1;
					}
				} else{
					if(file.getFieldName().equals("reiid")) {rei_id = Integer.parseInt(file.getString());}
					else if(file.getFieldName().equals("grader")) {grade_received = file.getString();}
			}
			
		}	
		
			
			Attachments attachment = new Attachments(rei_id,attachments);
			
			int status = DataService.addGrade(attachment, grade_received, grade_bit);
			
			HTMLTemplates.headers(out);
			HTMLTemplates.navbarEmp(out);
			out.print("<h1>SUCCESS! YOUR GRADE HAS BEEN ADDED! PLEASE RETURN TO THE HOME PAGE.</h1>");
			HTMLTemplates.goBackButton(out);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		catch (FileUploadException e) {
			e.printStackTrace();
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
		
		
	}

}
