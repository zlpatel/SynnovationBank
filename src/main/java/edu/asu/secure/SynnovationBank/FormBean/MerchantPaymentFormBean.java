package edu.asu.secure.SynnovationBank.FormBean;
import org.springframework.web.multipart.MultipartFile;

import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;

public class MerchantPaymentFormBean {
	


		private String notifications;
		private long notification_id;
		private long transaction_id;
		private float amount;
		private String emp_admin_flag;
		private String resolved_flag;
		MultipartFile file;
		
		
		public String getResolved_flag() {
			return resolved_flag;
		}
		public void setResolved_flag(String resolved_flag) {
			this.resolved_flag = resolved_flag;
		}
		public MultipartFile getFile() {
			return file;
		}
		public void setFile(MultipartFile file) {
			this.file = file;
		}
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
		public String getNotifications() {
			return notifications;
		}
		public void setNotifications(String notification) {
			this.notifications = notification;
		}
		
		
}


