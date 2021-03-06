package com.reimbursement.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.models.Employee;
import com.reimbursement.models.Expense;
import com.reimbursement.service.ExpenseService;

public class EmployeeController {
	private static ExpenseService expServ = new ExpenseService();
	private static Logger log = Logger.getLogger(EmployeeController.class);
	public static long millisecondTime = System.currentTimeMillis();
	public static Date currentDate = new java.sql.Date(millisecondTime);
	
	public static void getEmployeeHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			RequestDispatcher redis = request.getRequestDispatcher("/html/employeeHome.html");
			redis.forward(request, response);
		}
	public static void getEmployeeExpenses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		HttpSession session = request.getSession(false);
		Employee loggedInEmployee = (Employee)session.getAttribute("loggedInEmployee");
		log.info("loggedInEmployee from session attribute: " + loggedInEmployee);
		if(session != null) {
			List<Expense> employeeExpenses = expServ.selectExpensesByEmployeeId(loggedInEmployee.getEmployee_id());
			ObjectMapper om = new ObjectMapper();
			response.getWriter().write(om.writeValueAsString(employeeExpenses));
		}	
	}
	public static void insertNewEmployeeExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		Employee loggedInEmployee = (Employee)session.getAttribute("loggedInEmployee");
		log.info("loggedInEmployee sessionAttribute from insertNewEmployeeExpense: " + loggedInEmployee);
		if(session != null) {
			ObjectMapper om = new ObjectMapper();
			Expense newEmployeeExpense = om.readValue(request.getReader(), com.reimbursement.models.Expense.class);
			newEmployeeExpense.setEmployee_id(loggedInEmployee.getEmployee_id());
			newEmployeeExpense.setSubmitted(currentDate);
			newEmployeeExpense.setStatus("p");
			log.info("NEW EMPLOYEE EXPENSE: " + newEmployeeExpense);
			expServ.createEmployeeExpense(newEmployeeExpense);
		}
		
	}
	public static void deleteExpense(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		Employee loggedInEmployee = (Employee) session.getAttribute("loggedInEmployee");
		log.info("loggedInEmployee sessionAttribute from deleteExpense: " + loggedInEmployee);
		if(session != null) {
			ObjectMapper om = new ObjectMapper();
			Expense deletedExpense = om.readValue(request.getReader(),com.reimbursement.models.Expense.class);
			log.info("Expense to be DELETED: " + expServ.selectExpenseByExpenseId(deletedExpense.getExpense_id()));
			expServ.deleteExpenseById(deletedExpense.getExpense_id());
		}
	}

}
