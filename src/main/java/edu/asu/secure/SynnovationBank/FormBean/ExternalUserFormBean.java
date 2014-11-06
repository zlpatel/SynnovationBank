package edu.asu.secure.SynnovationBank.FormBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.ReportedIssues;
import edu.asu.secure.SynnovationBank.DTO.Role;

public class ExternalUserFormBean {

	private String fname;
	private String lname;
	private String username;
	private String password;
	private Date dateOfBirth;
	private String email;
	private String address;
	private String sitekey;
	private String oneTimePassword;
	private Date otpExpiry;
	private String allowAccessFlag;
	private String role;
	
	private long accountNumber;
	private float accountBalance;

	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public float getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAllowAccessFlag() {
		return allowAccessFlag;
	}
	public void setAllowAccessFlag(String allowAccessFlag) {
		this.allowAccessFlag = allowAccessFlag;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println("Format 2: " + dateFormatter.format(dateOfBirth));
		dateFormatter.format(dateOfBirth);
		this.dateOfBirth = dateOfBirth;
		}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSitekey() {
		return sitekey;
	}
	public void setSitekey(String sitekey) {
		this.sitekey = sitekey;
	}
	public String getOneTimePassword() {
		return oneTimePassword;
	}
	public void setOneTimePassword(String oneTimePassword) {
		this.oneTimePassword = oneTimePassword;
	}
	public Date getOtpExpiry() {
		return otpExpiry;
	}
	public void setOtpExpiry(Date otpExpiry) {
		this.otpExpiry = otpExpiry;
	}
	
	
}
