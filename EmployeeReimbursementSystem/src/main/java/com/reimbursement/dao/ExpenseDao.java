package com.reimbursement.dao;

import java.util.List;

import com.reimbursement.models.Expense;

public interface ExpenseDao {
	//CRUD
	//create
	public boolean insertExpense(Expense e);
	//read
	public List<Expense> selectAllExpenses();
	public Expense selectExpenseByExpenseId(int id);
	public List<Expense> selectExpensesByEmployeeId(int id);
	//update
	public void approveExpenseById(int id);
	public void denyExpenseById(int id);
	public void updateExpense(Expense e);
	//delete
	public boolean deleteExpense(int id);
}
