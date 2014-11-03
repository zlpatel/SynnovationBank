package edu.asu.secure.SynnovationBank.FormBean;

import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;

public class EmpNotifFormBean {

	private String firstName;
	private String lastName;
	private String userName;
	private String notifications;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
		
	}
	
	public String getNotifications() {
		return notifications;
	}
	public void setNotifications(NotificationsType notificationsType) {
		this.notifications = notificationsType.getNotificationType();
	}
	
	
	
	


}
