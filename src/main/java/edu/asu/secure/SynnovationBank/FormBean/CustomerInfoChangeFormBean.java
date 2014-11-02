package edu.asu.secure.SynnovationBank.FormBean;

public class CustomerInfoChangeFormBean {
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
	private String email;
	

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName ;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName ;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName ;
	}

	public void setEmail(String email) {
		this.email = email ;
	}

	public void setAddress(String address) {
		this.address = address ;
	}
	
	
	
}
