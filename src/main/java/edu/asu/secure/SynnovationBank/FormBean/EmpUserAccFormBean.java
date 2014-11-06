package edu.asu.secure.SynnovationBank.FormBean;



public class EmpUserAccFormBean {
	
	private String firstName;
	private String lastName;
	private long getAccountNumber;
	private float getBalance;
	private String userName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getGetAccountNumber() {
		return getAccountNumber;
	}
	public void setGetAccountNumber(long accountNumber) {
		this.getAccountNumber = accountNumber;
	}
	public float getGetBalance() {
		return getBalance;
	}
	public void setGetBalance(float getBalance) {
		this.getBalance = getBalance;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	} 

}
