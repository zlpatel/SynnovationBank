package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionDetailsDAO;
import edu.asu.secure.SynnovationBank.FormBean.MerchantTransactionFormBean;
import edu.asu.secure.SynnovationBank.Service.MerchantTransactionService;

@Service
@Transactional
public class MerchantTransactionServiceImpl implements MerchantTransactionService{

	@Autowired
	PersonDAO personDAO;
	@Autowired
	TransactionDetailsDAO transactionDetailsDao;
	
	@Override
	public List<MerchantTransactionFormBean> getTransactions(String userName) {
		Person sender = personDAO.fetchUserById(userName);
		System.out.println("FETCHED USER "+sender.getFirstName()+" "+sender.getLastName());
		long accNum=sender.getAccount().getAccountNumber(); 
		
		MerchantTransactionFormBean merchanttransactionFormBean=null;
    	
    	List<TransactionDetails> transactions=transactionDetailsDao.fetchAccountTransactions(accNum, 10);
    	
    	List<MerchantTransactionFormBean> list=new ArrayList<MerchantTransactionFormBean>();
    	
    	for(TransactionDetails trans: transactions){
    		merchanttransactionFormBean = new MerchantTransactionFormBean();
    		
    		merchanttransactionFormBean.setBalance(trans.getTransactions().getAmount());
    		merchanttransactionFormBean.setTransactionsName(trans.getTransactionType().getTransactionName());
    		merchanttransactionFormBean.setTransactionId(trans.getTransactions().getTransactionId());
    		merchanttransactionFormBean.setTransactionDate(trans.getTransactions().getDate());
    		merchanttransactionFormBean.setTransactionStatus(trans.getTransactions().getCompleteFlag());
    		
    		list.add(merchanttransactionFormBean);
    	}
    	return list;
	}

	@Override
	public String availableBalance(String userName) {
		// TODO Auto-generated method stub
		Person sender = personDAO.fetchUserById(userName);
		System.out.println("FETCHED USER "+sender.getFirstName()+" "+sender.getLastName());
		float balance=sender.getAccount().getBalance();
		String bal=String.valueOf(balance);
		return bal;
	}

	@Override
	public String getUserName(String userName) {
		Person sender = personDAO.fetchUserById(userName);
		System.out.println("FETCHED USER "+sender.getFirstName()+" "+sender.getLastName());
		String fullname=sender.getFirstName();
		return fullname;
	}
	}


