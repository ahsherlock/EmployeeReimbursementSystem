package com.reimbursement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.reimbursement.models.Expense;
import com.reimbursement.utilities.ConnectionFactory;

public class ExpenseDaoImp implements ExpenseDao {
	public static long millisecondTime = System.currentTimeMillis();
	public static Date currentDate = new java.sql.Date(millisecondTime);

	public boolean insertExpense(Expense e) {
		String sql = "INSERT INTO expenses(employee_id,type, amount, submitted, status, description) "+
				"values (?,?,?,?,?,?);";
	try(Connection conn = ConnectionFactory.getConnection()){
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, e.getEmployee_id());
		ps.setString(2, e.getType());
		ps.setDouble(3, e.getAmount());
		ps.setDate(4, e.getSubmitted());
		ps.setString(5, e.getStatus());
		ps.setString(6, e.getDescription());
		ps.execute();
		
	}catch(SQLException exception) {
		System.out.println("Expense was not added successfully");
		exception.printStackTrace();
		return false;
	}
		System.out.println("Expense was successfully added.");
		return true;
	}

	public List<Expense> selectAllExpenses() {
		List<Expense> expenseList = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * from expenses;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				expenseList.add(new Expense(
						rs.getInt("expense_id"),
						rs.getInt("employee_id"),
						rs.getString("type"),
						rs.getDouble("amount"),
						rs.getDate("submitted"),
						rs.getDate("resolved"),
						rs.getString("status"),
						rs.getString("description")
						));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return expenseList;
	}

	public Expense selectExpenseByExpenseId(int id) {
		List<Expense> expenseList = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * from expenses WHERE expense_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				expenseList.add(new Expense(
						rs.getInt("expense_id"),
						rs.getInt("employee_id"),
						rs.getString("type"),
						rs.getDouble("amount"),
						rs.getDate("submitted"),
						rs.getDate("resolved"),
						rs.getString("status"),
						rs.getString("description")
						));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return expenseList.get(0);
	}

	public List<Expense> selectExpensesByEmployeeId(int id) {
		List<Expense> expenseList = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * from expenses WHERE employee_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				expenseList.add(new Expense(
						rs.getInt("expense_id"),
						rs.getInt("employee_id"),
						rs.getString("type"),
						rs.getDouble("amount"),
						rs.getDate("submitted"),
						rs.getDate("resolved"),
						rs.getString("status"),
						rs.getString("description")
						));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return expenseList;
	}

	public void updateExpense(Expense e) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "UPDATE expense SET employee_id = ?, type = ?, amount = ?, submitted = ?, resolved = ?, status = ?, description = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,e.getEmployee_id());
			ps.setString(2, e.getType());
			ps.setDouble(3,e.getAmount());
			ps.setDate(4,e.getSubmitted());
			ps.setDate(5,e.getResolved());
			ps.setString(6,e.getStatus());
			ps.setString(7,e.getDescription());
		}catch(SQLException exception) {
			exception.printStackTrace();
		}

	}

	public boolean deleteExpense(int id) {
		Connection conn = ConnectionFactory.getConnection();
		String mySQL = "DELETE FROM expenses WHERE expense_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(mySQL);
			ps.setInt(1,id);
			ps.execute();
		}catch(SQLException ex) {
			System.out.println("Failure to delete");
			ex.printStackTrace();
			return false;
		}
		System.out.println("Expense deleted");
		return true;
	}

	@Override
	public void approveExpenseById(int id) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "UPDATE expenses SET status = 'c', resolved = ? where expense_id =?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, currentDate);
			ps.setInt(2, id);
			ps.execute();
		}catch(SQLException exception) {
			exception.printStackTrace();
		}

	}
		
	

	@Override
	public void denyExpenseById(int id) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "UPDATE expenses SET status = 'd', resolved = ? where expense_id =?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, currentDate);
			ps.setInt(2, id);
			ps.execute();
		}catch(SQLException exception) {
			exception.printStackTrace();
		}
		
	};
}


