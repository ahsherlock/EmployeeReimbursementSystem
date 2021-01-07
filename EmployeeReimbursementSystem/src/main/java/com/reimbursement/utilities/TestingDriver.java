package com.reimbursement.utilities;

import com.reimbursement.models.Employee;
import com.reimbursement.service.EmployeeService;
import com.reimbursement.service.ExpenseService;

public class TestingDriver {
	
	public static EmployeeService empServ = new EmployeeService();
	public static ExpenseService expServ = new ExpenseService();
	
	public static void main(String args[]) {
		
		//System.out.println(empServ.getEmployeeByUsername("alecusername"));
		//Employee loggedInEmployee = empServ.getEmployeeByUsername("alecusername");
		//System.out.println(empServ.ValidateEmployee(loggedInEmployee.getUsername(), loggedInEmployee.getPassword()));
		//System.out.println("Alec expenses: " + expServ.selectExpensesByEmployeeId(loggedInEmployee.getEmployee_id()));
	}
}
