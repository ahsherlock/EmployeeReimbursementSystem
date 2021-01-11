package com.reimbursement.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reimbursement.controllers.AdminController;
import com.reimbursement.dao.ExpenseDao;
import com.reimbursement.dao.ExpenseDaoImp;
import com.reimbursement.models.Expense;

public class ExpenseService {
	public static ExpenseDao expDao = new ExpenseDaoImp();
	private static Logger log = Logger.getLogger(ExpenseService.class);
	
	public Expense selectExpenseByExpenseId(int id){
		Expense expense = expDao.selectExpenseByExpenseId(id);
		log.info(expense);
		return expense;
	}
	public List<Expense> selectAllExpensesFromEmployees(){
		ArrayList<Expense> allEmployeeExpenses = (ArrayList<Expense>) expDao.selectAllExpenses();
		return allEmployeeExpenses;
	}
	
	public List<Expense> selectExpensesByEmployeeId(int id){
		ArrayList<Expense> expensesList = (ArrayList<Expense>)expDao.selectExpensesByEmployeeId(id);
		log.info(expensesList);
		return expensesList;
	}
	public Expense selectExpensesById(int id){
	Expense singleExpense = expDao.selectExpenseByExpenseId(id);
		log.info(singleExpense);
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
	public void deleteExpenseById(int id) {
		expDao.deleteExpense(id);
	}
}
