package edu.asu.secure.SynnovationBank.FormBean;

import java.util.Date;

public class UserTransactionFormBean {
	
	private float balance;
	private String transactionsName;
	private long transactionId;
	private Date transactionDate;
	private String userName;
	
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getTransactionsName() {
		return transactionsName;
	}
	public void setTransactionsName(String transactionsName) {
		this.transactionsName = transactionsName;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getUserName() {
		
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}