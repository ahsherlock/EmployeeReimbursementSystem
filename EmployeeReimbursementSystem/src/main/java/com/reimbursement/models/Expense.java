package com.reimbursement.models;

import java.sql.Date;

public class Expense {
	private int expense_id;
	private int employee_id;
	private String type;
	private double amount;
	private Date submitted;
	private Date resolved;
	private String status;
	private String description;
	public Expense(int expense_id, int employee_id, String type, double amount, Date submitted, Date resolved,
			String status, String description) {
		super();
		this.expense_id = expense_id;
		this.employee_id = employee_id;
		this.type = type;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.status = status;
		this.description = description;
	}
	public Expense( int employee_id, String type, double amount, Date submitted, Date resolved,
			String status, String description) {
		super();
		this.employee_id = employee_id;
		this.type = type;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.status = status;
		this.description = description;
	}
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getExpense_id() {
		return expense_id;
	}
	public void setExpense_id(int expense_id) {
		this.expense_id = expense_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}
	public Date getResolved() {
		return resolved;
	}
	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + employee_id;
		result = prime * result + expense_id;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (expense_id != other.expense_id)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Expense [expense_id=" + expense_id + ", employee_id=" + employee_id + ", type=" + type + ", amount="
				+ amount + ", submitted=" + submitted + ", resolved=" + resolved + ", status=" + status
				+ ", description=" + description + "]";
	}
	
	
	
}
