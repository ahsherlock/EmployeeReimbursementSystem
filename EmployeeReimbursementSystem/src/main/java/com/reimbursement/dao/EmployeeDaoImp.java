package com.reimbursement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reimbursement.controllers.AdminController;
import com.reimbursement.models.Employee;
import com.reimbursement.utilities.ConnectionFactory;

public class EmployeeDaoImp implements EmployeeDao {
	private static Logger log = Logger.getLogger(EmployeeDaoImp.class);

	public List<Employee> selectAllEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * from employee;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				employeeList.add(new Employee(
						rs.getInt("employee_id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("first"),
						rs.getString("last"),
						rs.getString("rank")
						));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	public Employee selectEmployeeByUsername(String username) {
		List<Employee> employeeList = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * from employee WHERE username = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				employeeList.add(new Employee(
						rs.getInt("employee_id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("first"),
						rs.getString("last"),
						rs.getString("rank")
						));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return employeeList.get(0);
	}

	public Employee selectEmployeeByEmployeeId(int id) {
		List<Employee> employeeList = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * from employee WHERE employee_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				employeeList.add(new Employee(
						rs.getInt("employee_id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("first"),
						rs.getString("last"),
						rs.getString("rank")
						));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return employeeList.get(0);
	}

	public boolean insertEmployee(Employee emp) {
		String sql = "INSERT INTO employee(username, password, first_name, last_name) "+
				"values (?,?,?,?);";
	try(Connection conn = ConnectionFactory.getConnection()){
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emp.getUsername());
		ps.setString(2, emp.getPassword());
		ps.setString(3, emp.getFirst());
		ps.setString(4, emp.getLast());
		ps.execute();
		
	}catch(SQLException e) {
		log.info("Emp was not added successfully");
		e.printStackTrace();
		return false;

		
	}
	return true;
	}

	public boolean insertEmployeeWithRank(Employee emp) {
		String sql = "INSERT INTO employee(username, password, first_name, last_name, employment_rank) "+
				"values (?,?,?,?,?);";
	try(Connection conn = ConnectionFactory.getConnection()){
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, emp.getUsername());
		ps.setString(2, emp.getPassword());
		ps.setString(3, emp.getFirst());
		ps.setString(4, emp.getLast());
		ps.setString(5, emp.getRank());
		ps.execute();
		
	}catch(SQLException e) {
		log.info("Emp was not added successfully");
		e.printStackTrace();
		return false;

		
	}
	return true;
	}

	public void updateEmployee(Employee emp) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "UPDATE employee SET username = ?, password = ?, first_name = ?, last_name = ?, employment_rank = ?, employee_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,emp.getUsername());
			ps.setString(2, emp.getPassword());
			ps.setString(3,emp.getFirst());
			ps.setString(4,emp.getLast());
			ps.setString(5,emp.getRank());
			ps.setInt(6,emp.getEmployee_id());
		}catch(SQLException exception) {
			exception.printStackTrace();
		}

	}

	public boolean deleteEmployee(int id) {
		Connection conn = ConnectionFactory.getConnection();
		String mySQL = "DELETE FROM employee WHERE employee_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(mySQL);
			ps.setInt(1, id);
			ps.execute();
		}catch(SQLException ex) {
			log.info("Failure to delete");
			ex.printStackTrace();
			return false;
		}
		log.info("Employee deleted");
		return true;
	}
}


