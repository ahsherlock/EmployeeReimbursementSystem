package com.reimbursement.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.models.Employee;
import com.reimbursement.models.Expense;
import com.reimbursement.service.ExpenseService;

public class AdminController {
	private static ExpenseService expServ = new ExpenseService();
	private static Logger log = Logger.getLogger(AdminController.class);
	
	public static long millisecondTime = System.currentTimeMillis();
	public static Date currentDate = new java.sql.Date(millisecondTime);
	
	public static void getAdminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher redis = request.getRequestDispatcher("/html/adminHome.html");
		redis.forward(request, response);
	}
	
	public static void getAllEmployeeExpenses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		response.setContentType("application/json");
		HttpSession session = request.getSession(false);
		Employee loggedInEmployee = (Employee)session.getAttribute("loggedInEmployee");
		log.info("loggedInEmployee from session attribute: " + loggedInEmployee);
		if(session != null) {
			List<Expense> allEmployeeExpenses = expServ.selectAllExpensesFromEmployees();
			ObjectMapper om = new ObjectMapper();
			response.getWriter().write(om.writeValueAsString(allEmployeeExpenses));
		}	
	}
	public static void denyExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		Employee loggedInEmployee = (Employee)session.getAttribute("loggedInEmployee");
		log.info("loggedInEmployee from session attribute: " + loggedInEmployee);

		log.info("MADE IT TO DENY EXPENSE");

		ObjectMapper om = new ObjectMapper();
		Expense fakeExpense = om.readValue(request.getReader(), com.reimbursement.models.Expense.class);
		Expense actualExpense = expServ.selectExpenseByExpenseId(fakeExpense.getExpense_id());
		actualExpense.setResolved(currentDate);
		log.info("NEWLY CHANGED DATE EXPENSE DENY: "+actualExpense);
		expServ.denyTicketById(actualExpense.getExpense_id());
		
		
		
	}
	public static void approveExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		Employee loggedInEmployee = (Employee)session.getAttribute("loggedInEmployee");
		log.info("loggedInEmployee from session attribute: " + loggedInEmployee);
		log.info("MADE IT TO APPROVE EXPENSE");
		ObjectMapper om = new ObjectMapper();
		Expense fakeExpense = om.readValue(request.getReader(), com.reimbursement.models.Expense.class);
		Expense actualExpense = expServ.selectExpenseByExpenseId(fakeExpense.getExpense_id());
		actualExpense.setResolved(currentDate);
		log.info("NEWLY CHANGED DATE EXPENSE APPROVE: "+actualExpense);
		expServ.approveTicketById(actualExpense.getExpense_id());
		
		
		
	}
}
