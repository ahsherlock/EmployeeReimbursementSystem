package com.reimbursement.service;

import java.util.ArrayList;
import java.util.List;

import com.reimbursement.dao.ExpenseDao;
import com.reimbursement.dao.ExpenseDaoImp;
import com.reimbursement.models.Expense;

public class ExpenseService {
	public static ExpenseDao expDao = new ExpenseDaoImp();
	
	
	public List<Expense> selectAllExpensesFromEmployees(){
		ArrayList<Expense> allEmployeeExpenses = (ArrayList<Expense>) expDao.selectAllExpenses();
		return allEmployeeExpenses;
	}
	
	public List<Expense> selectExpensesByEmployeeId(int id){
		ArrayList<Expense> expensesList = (ArrayList<Expense>)expDao.selectExpensesByEmployeeId(id);
		System.out.println(expensesList);
		return expensesList;
	}
	public Expense selectExpensesById(int id){
	Expense singleExpense = expDao.selectExpenseByExpenseId(id);
		System.out.println(singleExpense);
		return singleExpense;
	}
	public void createEmployeeExpense(Expense newEmployeeExpense) {
		expDao.insertExpense(newEmployeeExpense);
	}
	public void approveTicketById(int id) {
		expDao.approveExpenseById(id);
	}
	public void denyTicketById(int id) {
		expDao.denyExpenseById(id);
	}
}
