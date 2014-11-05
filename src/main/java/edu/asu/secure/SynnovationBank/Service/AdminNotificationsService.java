package edu.asu.secure.SynnovationBank.Service;
import edu.asu.secure.SynnovationBank.FormBean.*;

import java.util.List;

public interface AdminNotificationsService {
	
	public List<AdminCriticalTransactionsFormBean> getCriticalTransactionNotifications();
	public List<AdminPIIRequestsFormBean> getPIIRequestNotifications();
    public void sendTransactionDeclinedNotification(String userName);
    public void sendTransactionAcceptedNotification(AdminCriticalTransactionsFormBean criticalTransactionFormBean);

}
