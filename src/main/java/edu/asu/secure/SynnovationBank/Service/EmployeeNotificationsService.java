package edu.asu.secure.SynnovationBank.Service;
import edu.asu.secure.SynnovationBank.FormBean.*;

import java.util.List;

public interface EmployeeNotificationsService {
	public List<EmpNotifFormBean> notifications();
	
	public List<AdminCriticalTransactionsFormBean> merchantRequestsCriticalTrans();

    public void sendTransactionDeclinedNotification(String userName,long tId, long nId);

	public void sendTransactionAcceptedNotification(String userName, long transactionId, long notificationId);
}
