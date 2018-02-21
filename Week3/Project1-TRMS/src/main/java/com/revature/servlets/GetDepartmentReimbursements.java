package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Reimbursement;
import com.revature.service.DataService;

/**
 * Servlet implementation class GetDepartmentReimbursements
 */
public class GetDepartmentReimbursements extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String department = (String)session.getAttribute("department");
		
		List<Reimbursement> reim = DataService.departmentReimbursements(department);
		
		if(reim!=null){
			String myXml = "<root>";
			//Manually create the xml file.
			for(Reimbursement r : reim){
				myXml += "<departmentreimbursement>"
						+ "<e_id>" + r.getEmp_id() + "</e_id>" +
						"<r_fname>" + r.getFname() + "</r_fname>" +
						"<r_lname>" + r.getLname() + "</r_lname>" +
						 "<r_id>" + r.getRei_id() + "</r_id>" +
						"<r_date>" + r.getDateOf() + "</r_date>" +
						"<r_time>" + r.getTime() + "</r_time>" +
						"<r_location>" + r.getLocation() + "</r_location>" +
						"<r_description>" + r.getDesc() + "</r_description>" +
						"<r_cost>" + r.getCost() + "</r_cost>" + 
						"<r_gradingFormat>" + r.getGradingFormat() +  "</r_gradingFormat>" +
						"<r_typeOfEvent>" + r.getTypeOfEvent()  +  "</r_typeOfEvent>" +
						"<r_workRelatedJustification>" + r.getWork_related_justification() +  "</r_workRelatedJustification>" +
						"<r_approvalState>" + r.getAprroval_state()  +  "</r_approvalState>" +
						"<r_note>" + r.getNote()  +  "</r_note>" +
						"</departmentreimbursement>";
			}
			myXml += "</root>";
			
			out.print(myXml); //State == 4
		}else{
			out.print("<root></root>"); //State == 4
		}
	}

}
