package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionDetailsDAO;
import edu.asu.secure.SynnovationBank.FormBean.CustomertransactionFormBean;
import edu.asu.secure.SynnovationBank.Service.CustomerTransactionService;

@Service
@Transactional
public class CustomerTransactionServiceImpl implements CustomerTransactionService{

	@Autowired
	PersonDAO personDAO;
	@Autowired
	TransactionDetailsDAO transactionDetailsDao;
	
	@Override
	public List<CustomertransactionFormBean> getTransactions(String userName) {
		Person sender = personDAO.fetchUserById(userName);
		System.out.println("FETCHED USER "+sender.getFirstName()+" "+sender.getLastName());
		long accNum=sender.getAccount().getAccountNumber(); 
		
		CustomertransactionFormBean customertransactionFormBean=null;
    	
    	List<TransactionDetails> transactions=transactionDetailsDao.fetchAccountTransactions(accNum, 10);
    	
    	List<CustomertransactionFormBean> list=new ArrayList<CustomertransactionFormBean>();
    	
    	for(TransactionDetails trans: transactions){
    		customertransactionFormBean = new CustomertransactionFormBean();
    		
    		customertransactionFormBean.setBalance(trans.getTransactions().getAmount());
    		customertransactionFormBean.setTransactionsName(trans.getTransactionType().getTransactionName());
    		customertransactionFormBean.setTransactionId(trans.getTransactions().getTransactionId());
    		customertransactionFormBean.setTransactionDate(trans.getTransactions().getDate());
    		list.add(customertransactionFormBean);
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


