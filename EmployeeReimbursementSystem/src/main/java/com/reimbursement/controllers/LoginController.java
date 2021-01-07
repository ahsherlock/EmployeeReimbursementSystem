package com.reimbursement.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.reimbursement.models.Employee;
import com.reimbursement.service.EmployeeService;

public class LoginController {
	private static EmployeeService empServ = new EmployeeService();
	public static void getLandingPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher redis = request.getRequestDispatcher("/html/login.html");
		redis.forward(request, response);
		
	}
	public static void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(request.getMethod().equals("POST")) {
			if(empServ.ValidateEmployee(request.getParameter("username"), request.getParameter("password"))) {
				System.out.println(empServ.getEmployeeByUsername(request.getParameter("username")).getRank());
				Employee loggedInEmployee = empServ.getEmployeeByUsername(request.getParameter("username"));
				HttpSession session = request.getSession();
				session.setAttribute("loggedInEmployee", loggedInEmployee);
				System.out.println("THIS IS THE LOGGED IN EMPLOYEE STORED IN SESSION: " + loggedInEmployee);
				switch(empServ.getEmployeeByUsername(request.getParameter("username")).getRank()) {
				
				case "e":
					response.sendRedirect("http://localhost:8080/EmployeeReimbursementSystem/api/employeehome");
					break;
				case "a":
					response.sendRedirect("http://localhost:8080/EmployeeReimbursementSystem/api/adminhome");
					break;
				}
				
			}else {
				response.setStatus(403);
				response.sendRedirect("http://localhost:8080/EmployeeReimbursementSystem/api/home");
			}
		}else {
			response.setStatus(405);
			response.sendRedirect("http://localhost:8080/EmployeeReimbursementSystem/api/home");
		}
	}
	public static void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("http://localhost:8080/EmployeeReimbursementSystem/api/landing");
		
	}
	
}
