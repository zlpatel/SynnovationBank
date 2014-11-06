package edu.asu.secure.SynnovationBank.FormBean;
import org.springframework.web.multipart.MultipartFile;

import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;

public class MerchantPaymentFormBean {
	

		private long notification_id;
		private long transaction_id;
		private float amount;
		MultipartFile file;
		
		
		public MultipartFile getFile() {
			return file;
		}
		public void setFile(MultipartFile file) {
			this.file = file;
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
			
}


