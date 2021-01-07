package com.reimbursement.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController {
	public static void getHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession(false)!=null) {
			RequestDispatcher redis = request.getRequestDispatcher("/html/home.html");
			System.out.println("We loggin this guy in now.");
			redis.forward(request, response);
			
		}else {
			response.sendRedirect("http://localhost:8080/EmployeeReimbursementSystem/api/landing");
		}

	}
	public static void resetToHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//we're routing all the invalid requests to the landing page.
		RequestDispatcher redis = request.getRequestDispatcher("/api/landing");
		
		redis.forward(request, response);
	}
}
