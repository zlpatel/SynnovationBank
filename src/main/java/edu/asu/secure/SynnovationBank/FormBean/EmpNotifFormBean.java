package edu.asu.secure.SynnovationBank.FormBean;

import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;

public class EmpNotifFormBean {

	private String firstName;
	private String lastName;
	private String notifications;
	
	public String getFirstname() {
		return firstName;
	}
	public void setFirstname(String firstName) {
		this.firstName = firstName;
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
