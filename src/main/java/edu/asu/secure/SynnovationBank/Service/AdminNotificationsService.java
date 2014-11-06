package edu.asu.secure.SynnovationBank.Service;
import edu.asu.secure.SynnovationBank.FormBean.*;

import java.util.List;

public interface AdminNotificationsService 
{	
	public List<AdminCriticalTransactionsFormBean> getCriticalTransactionNotifications();
	public List<AdminPIIRequestsFormBean> getPIIRequestNotifications();
	public void sendTransactionDeclinedNotification(String userId, long nId);
	public void sendTransactionAcceptedNotification(String userId, long tId, long nId);
}
