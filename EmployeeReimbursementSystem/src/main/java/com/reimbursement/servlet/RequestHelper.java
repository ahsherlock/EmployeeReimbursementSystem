package com.reimbursement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reimbursement.controllers.AdminController;
import com.reimbursement.controllers.EmployeeController;
import com.reimbursement.controllers.HomeController;
import com.reimbursement.controllers.LoginController;

public class RequestHelper {
	public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String endpoint = request.getRequestURI();
		System.out.println("endpoint at start of request helper process: " + endpoint);
		System.out.println("request method at start of request helper process: " + request.getMethod());
		switch(endpoint) {
		case "/EmployeeReimbursementSystem/api/home":
			HomeController.getHomePage(request, response);
			break;
		case "/EmployeeReimbursementSystem/api/landing":
			LoginController.getLandingPage(request,response);
			break;
		case "/EmployeeReimbursementSystem/api/login":
			LoginController.login(request,response);
			break;
		case "/EmployeeReimbursementSystem/api/adminhome":
			System.out.println("made it to /api/adminhome end point");
			AdminController.getAdminHome(request,response);
			break;
		case "/EmployeeReimbursementSystem/api/adminhome/expenses":
			switch(request.getMethod()) {
			case "GET":
				AdminController.getAllEmployeeExpenses(request, response);
				break;
			case "POST":
				AdminController.getAllEmployeeExpenses(request, response);
				break;
			}
			break;
		case "/EmployeeReimbursementSystem/api/adminhome/approve":
			AdminController.approveExpense(request, response);
			break;
		case "/EmployeeReimbursementSystem/api/adminhome/deny":
			AdminController.denyExpense(request, response);
			break;
		case "/EmployeeReimbursementSystem/api/employeehome":
			EmployeeController.getEmployeeHome(request,response);
			break;
		case "/EmployeeReimbursementSystem/api/employeehome/expenses":
			System.out.println("Made it to employee expenses endpoint");
			switch(request.getMethod()) {
			case "GET":
				System.out.println("Made it to employee expenses GET endpoint");
				EmployeeController.getEmployeeExpenses(request,response);
				break;
			case "POST":
				System.out.println("Made it to employee expenses POST endpoint");
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
