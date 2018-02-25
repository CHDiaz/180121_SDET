package com.project1.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project1.services.AccountServices;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login servlet reached");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email + password);
		RequestDispatcher rd;
		//HttpSession session = request.getSession();
		
		if(AccountServices.validate(email, password)){
			//session.setAttribute("username", email);
			System.out.println("sloop gang");
			rd = request.getRequestDispatcher("accountpage.jsp");
			rd.forward(request, response);
		}else{
			//session.invalidate();
			rd = request.getRequestDispatcher("signinerror.jsp");
			rd.forward(request, response);
			
		}
	}

}