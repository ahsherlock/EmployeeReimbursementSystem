package com.reimbursement.dao;

import java.util.List;

import com.reimbursement.models.Employee;

public interface EmployeeDao {
	public List<Employee> selectAllEmployees();
	public Employee selectEmployeeByUsername(String username);
	public Employee selectEmployeeByEmployeeId(int id);
	public boolean insertEmployee(Employee emp);
	public boolean insertEmployeeWithRank(Employee emp);
	public void updateEmployee(Employee emp);
	public boolean deleteEmployee(int id);
}
