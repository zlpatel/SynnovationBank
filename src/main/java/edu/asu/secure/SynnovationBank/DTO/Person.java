package edu.asu.secure.SynnovationBank.DTO;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Person")
public class Person {

	private String userId;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date dateOfBirth;
	private String email;
	private String address;
	private String sitekey;
	private String oneTimePassword;
	private Date otpExpiry;
	
	private Role role;
	private Set<Notifications> notifications;
	private Set<Account> account;
	private Set<ReportedIssues> issues;

	@Id
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="password", length=255, nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="first_name", length = 255, nullable=false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="middle_name", length = 255)
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name="last_name", length = 255, nullable=false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="date_of_birth", nullable=false)
	@Temporal(TemporalType.DATE)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name="email", length = 255, nullable=false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="address", length = 255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="sitekey", length = 255)
	public String getSitekey() {
		return sitekey;
	}

	public void setSitekey(String sitekey) {
		this.sitekey = sitekey;
	}
	
	@Column(name="onetime_password", length = 255)
	public String getOneTimePassword() {
		return oneTimePassword;
	}

	public void setOneTimePassword(String oneTimePassword) {
		this.oneTimePassword = oneTimePassword;
	}

	@Column(name="otp_expiry")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOtpExpiry() {
		return otpExpiry;
	}

	public void setOtpExpiry(Date otpExpiry) {
		this.otpExpiry = otpExpiry;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="role_id", referencedColumnName="role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="assignee_id", referencedColumnName="user_id")
	public Set<Notifications> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notifications> notifications) {
		this.notifications = notifications;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name="Account_Holder",
			joinColumns={@JoinColumn(name="user_id", referencedColumnName="user_id")},
			inverseJoinColumns={@JoinColumn(name="account_number", referencedColumnName="account_number")})
	public Set<Account> getAccount() {
		return account;
	}

	public void setAccount(Set<Account> account) {
		this.account = account;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	public Set<ReportedIssues> getIssues() {
		return issues;
	}

	public void setIssues(Set<ReportedIssues> issues) {
		this.issues = issues;
	}

	
}
