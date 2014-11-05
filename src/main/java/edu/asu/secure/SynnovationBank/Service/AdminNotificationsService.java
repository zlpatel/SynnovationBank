package edu.asu.secure.SynnovationBank.Service;
import edu.asu.secure.SynnovationBank.FormBean.*;

import java.util.List;

public interface AdminNotificationsService {
	
	public List<AdminCriticalTransactionsFormBean> notifications();
    public void sendTransactionDeclinedNotification(String userName);
    public void sendTransactionAcceptedNotification(AdminCriticalTransactionsFormBean criticalTransactionFormBean);

}
