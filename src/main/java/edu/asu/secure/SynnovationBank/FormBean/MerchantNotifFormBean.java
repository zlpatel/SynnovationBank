package edu.asu.secure.SynnovationBank.FormBean;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;

public class MerchantNotifFormBean {
	


		private String firstName;
		private String lastName;
		private String userName;
		private String notifications;
		private long notification_id;
		private long transaction_id;
		private float amount;
		private String emp_admin_flag;
		
		
		
		public String getEmp_admin_flag() {
			return emp_admin_flag;
		}
		public void setEmp_admin_flag(String emp_admin_flag) {
			this.emp_admin_flag = emp_admin_flag;
		}
		public float getAmount() {
			return amount;
		}
		public void setAmount(float amount) {
			this.amount = amount;
		}
		public long getTransaction_id() {
			return transaction_id;
		}
		public void setTransaction_id(long transaction_id) {
			this.transaction_id = transaction_id;
		}
		public long getNotification_id() {
			return notification_id;
		}
		public void setNotification_id(long notification_id) {
			this.notification_id = notification_id;
		}
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
		public void setNotifications(String notification) {
			this.notifications = notification;
		}
		


	}


