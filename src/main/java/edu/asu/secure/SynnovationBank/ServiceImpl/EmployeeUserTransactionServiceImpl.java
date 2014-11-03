package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.DTO.Transactions;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionTypeDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionsDAO;
import edu.asu.secure.SynnovationBank.DaoImpl.AccountDAOImpl;
import edu.asu.secure.SynnovationBank.DaoImpl.TransactionsDAOImpl;
import edu.asu.secure.SynnovationBank.FormBean.EmpNotifFormBean;
import edu.asu.secure.SynnovationBank.FormBean.UserTransactionFormBean;
import edu.asu.secure.SynnovationBank.Service.EmployeeUserTransactionService;

@Service
@Transactional
public class EmployeeUserTransactionServiceImpl implements EmployeeUserTransactionService {
	 @Autowired
	  private AccountDAO accountDao ;
	 @Autowired
	 private TransactionsDAO transactionsDao ;
	     
	    
	 
	   
	    
		public  List<UserTransactionFormBean> getTransactions(long accountNumber)
		{
			
				UserTransactionFormBean userTransactionFormBean=null;
		    	//System.out.println("Hi it is here!");
		    	List<Transactions> transactions=transactionsDao.fetchTransactions(accountNumber);
		    	//System.out.println("Hi it is here 2!");
		    	List<UserTransactionFormBean> list=new ArrayList<UserTransactionFormBean>();
		    	
		    	for(Transactions trans: transactions){
		    		userTransactionFormBean = new UserTransactionFormBean();
		    		//Need to ask rohit
		    		userTransactionFormBean.setAmount(trans.getAmount());
		    		userTransactionFormBean.setTransactionId(trans.getTransactionId());
		    		userTransactionFormBean.setTransactionName(trans.getTransactionDetails();
		    		list.add(empNotifFormBean);
		    	}
		    	return list;
			}
			
			//return false;
			
			
			//return account.Person.flag
			
			//
			
		}





		@Override
		public boolean checkFlag(long accountNumber) {
			return accountDao.checkFlag(accountNumber);
		}

}
