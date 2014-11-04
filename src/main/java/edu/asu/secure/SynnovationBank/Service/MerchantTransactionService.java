package edu.asu.secure.SynnovationBank.Service;

import java.util.List;

import edu.asu.secure.SynnovationBank.FormBean.CustomertransactionFormBean;

public interface MerchantTransactionService {

	public List<CustomertransactionFormBean> getTransactions(String userName);
	public String availableBalance(String userName);
	public String getUserName(String userName);

}
