package com.reimbursement.service;

import org.apache.log4j.Logger;

import com.reimbursement.controllers.AdminController;
import com.reimbursement.dao.EmployeeDao;
import com.reimbursement.dao.EmployeeDaoImp;
import com.reimbursement.models.Employee;

public class EmployeeService {
	private EmployeeDao eDao = new EmployeeDaoImp();
	private static Logger log = Logger.getLogger(EmployeeService.class);
	
	
	public boolean ValidateEmployee(String username, String password) {
		Employee loggedInEmployee = eDao.selectEmployeeByUsername(username);
		if(loggedInEmployee.getPassword().equals(password)) {
			log.info("Employee matches");
			return true;
		}else {
			log.info("Employee Mismatch. Try again");
			return false;
		}
	}
	public Employee getEmployeeByUsername(String username) {
		Employee emp = new Employee();
		emp = eDao.selectEmployeeByUsername(username);
		return emp;
	}
	public int getEmployeeIdNumber(Employee e) {
		return e.getEmployee_id();
	}

}
