package com.reimbursement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.reimbursement.controllers.AdminController;
import com.reimbursement.controllers.EmployeeController;
import com.reimbursement.controllers.HomeController;
import com.reimbursement.controllers.LoginController;

public class RequestHelper {
	final static Logger log = Logger.getLogger(RequestHelper.class);
	public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String endpoint = request.getRequestURI();
		log.info("endpoint at start of request helper process: " + endpoint);
		log.info("request method at start of request helper process: " + request.getMethod());
		switch(endpoint) {
		case "/EmployeeReimbursementSystem/api/home":
			log.info("at the api/home endpoint.");
			HomeController.getHomePage(request, response);
			break;
		case "/EmployeeReimbursementSystem/api/error":
			log.info("at the api/ERROR endpoint.");
			HomeController.getErrorPage(request,response);
			break;
		case "/EmployeeReimbursementSystem/api/landing":
			log.info("at the api/landing endpoint.");
			LoginController.getLandingPage(request,response);
			break;
		case "/EmployeeReimbursementSystem/api/login":
			log.info("at the api/login endpoint.");
			LoginController.login(request,response);
			break;
		case "/EmployeeReimbursementSystem/api/logout":
			log.info("at the api/logout endpoint.");
			LoginController.logout(request,response);
			break;
		case "/EmployeeReimbursementSystem/api/adminhome":
			log.info("made it to /api/adminhome end point");
			AdminController.getAdminHome(request,response);
			break;
		case "/EmployeeReimbursementSystem/api/adminhome/expenses":
			switch(request.getMethod()) {
			case "GET":
				log.info("at adminhome/expenses with GET request.");
				AdminController.getAllEmployeeExpenses(request, response);
				break;
			case "POST":
				log.info("at adminhome/expenses with POST request.");
				AdminController.getAllEmployeeExpenses(request, response);
				break;
			}
			break;
		case "/EmployeeReimbursementSystem/api/adminhome/approve":
			log.info("at adminhome/deny.");
			AdminController.approveExpense(request, response);
			break;
		case "/EmployeeReimbursementSystem/api/adminhome/deny":
			log.info("at adminhome/deny.");
			AdminController.denyExpense(request, response);
			break;
		case "/EmployeeReimbursementSystem/api/employeehome":
			log.info("at /employeehome enpoint");
			EmployeeController.getEmployeeHome(request,response);
			break;
		case "/EmployeeReimbursementSystem/api/employeehome/delete":
			log.info("Made it to employeehome/DELETE endpoint");
			EmployeeController.deleteExpense(request, response);
			break;
		case "/EmployeeReimbursementSystem/api/employeehome/expenses":
			log.info("Made it to employee expenses endpoint");
			switch(request.getMethod()) {
			case "GET":
				log.info("Made it to employee expenses GET endpoint");
				EmployeeController.getEmployeeExpenses(request,response);
				break;
			case "POST":
				log.info("Made it to employee expenses POST endpoint");
				EmployeeController.insertNewEmployeeExpense(request,response);
				break;
			}
			break;
		case "/Reimbursement/api/admin":
			//AdminController.admin(request,response);
			break;
		case "/Reimbursement/api/employee.js":
			response.sendRedirect("http://localhost8080/Reimbursement/js/employee.js");
			break;
		default:
			HomeController.resetToHome(request, response);
		}
		
	}

}
