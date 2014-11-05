package edu.asu.secure.SynnovationBank.Service;

import java.util.List;

import edu.asu.secure.SynnovationBank.FormBean.MerchantTransactionFormBean;

public interface MerchantTransactionService {

	public List<MerchantTransactionFormBean> getTransactions(String userName);
	public String availableBalance(String userName);
	public String getUserName(String userName);

}
