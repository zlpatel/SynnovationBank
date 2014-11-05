package edu.asu.secure.SynnovationBank.Service;

import java.util.List;



import edu.asu.secure.SynnovationBank.FormBean.UserTransactionFormBean;

public interface EmployeeUserTransactionService {

	
	
	public List<UserTransactionFormBean> getTransactions(String userName);

	public boolean checkFlag(String userName);

	public void sendNotification(String UserName);

	


}
