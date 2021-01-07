package com.reimbursement.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.models.Employee;
import com.reimbursement.models.Expense;
import com.reimbursement.service.ExpenseService;

public class AdminController {
	private static ExpenseService expServ = new ExpenseService();
	
	public static void getAdminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher redis = request.getRequestDispatcher("/html/adminHome.html");
		redis.forward(request, response);
	}
	
	public static void getAllEmployeeExpenses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		HttpSession session = request.getSession(false);
		Employee loggedInEmployee = (Employee)session.getAttribute("loggedInEmployee");
		System.out.println("loggedInEmployee from session attribute: " + loggedInEmployee);
		if(session != null) {
			List<Expense> allEmployeeExpenses = expServ.selectAllExpensesFromEmployees();
			ObjectMapper om = new ObjectMapper();
			response.getWriter().write(om.writeValueAsString(allEmployeeExpenses));
		}	
	}
	public static void denyExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		Employee loggedInEmployee = (Employee)session.getAttribute("loggedInEmployee");
		System.out.println("loggedInEmployee from session attribute: " + loggedInEmployee);
		System.out.println("MADE IT TO DENY EXPENSE");
		ObjectMapper om = new ObjectMapper();
		Expense fakeExpense = om.readValue(request.getReader(), com.reimbursement.models.Expense.class);
		expServ.denyTicketById(fakeExpense.getExpense_id());
		
		
		
	}
	public static void approveExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		Employee loggedInEmployee = (Employee)session.getAttribute("loggedInEmployee");
		System.out.println("loggedInEmployee from session attribute: " + loggedInEmployee);
		System.out.println("MADE IT TO APPROVE EXPENSE");
		ObjectMapper om = new ObjectMapper();
		Expense fakeExpense = om.readValue(request.getReader(), com.reimbursement.models.Expense.class);
		System.out.println(expServ.selectExpensesById(fakeExpense.getExpense_id()));
		expServ.approveTicketById(fakeExpense.getExpense_id());
		
		
		
	}
}
