package edu.asu.secure.SynnovationBank.FormBean;

import edu.asu.secure.SynnovationBank.DTO.Person;

public class EmpUserAccFormBean {
	
	private String firstName;
	private String lastName;
	private long getAccountNumber;
	private float getBalance;
	
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
	public void setGetAccountNumber(long l) {
		this.getAccountNumber = l;
	}
	public float getGetBalance() {
		return getBalance;
	}
	public void setGetBalance(float f) {
		this.getBalance = f;
	} 

}
