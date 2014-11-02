package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.DTO.Transactions;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsTypeDAO;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionDetailsDAO;
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
	 private TransactionDetailsDAO transactionDetailsDao ;
	 @Autowired
	 private PersonDAO personDao;
	 @Autowired
	 private NotificationsTypeDAO notificationsTypeDAO; 
	 @Autowired
	 private NotificationsDAO notificationsDAO;
	     
	    
	 
	   
	    @Override
		public  List<UserTransactionFormBean> getTransactions(String userName)
		{
			System.out.println("This is retrieving the transactions");
			System.out.println("This is checking the flag");
			Person person= personDao.fetchUserById(userName);
			long accNum=person.getAccount().getAccountNumber();
				UserTransactionFormBean userTransactionFormBean=null;
		    	
		    	List<TransactionDetails> transactions=transactionDetailsDao.fetchAccountTransactions(accNum, 10);
		    	
		    	List<UserTransactionFormBean> list=new ArrayList<UserTransactionFormBean>();
		    	
		    	for(TransactionDetails trans: transactions){
		    		userTransactionFormBean = new UserTransactionFormBean();
		    		
		    		userTransactionFormBean.setBalance(trans.getAccount().getBalance());
		    		userTransactionFormBean.setTransactionsName(trans.getTransactionType().getTransactionName());
		    		userTransactionFormBean.setTransactionId(trans.getTransactions().getTransactionId());
		    		userTransactionFormBean.setTransactionDate(trans.getTransactions().getDate());
		    		list.add(userTransactionFormBean);
		    	}
		    	return list;
			}
			
		@Override
		public boolean checkFlag(String userName) {
			System.out.println("This is checking the flag");
			Person person= personDao.fetchUserById(userName);
			long accNum=person.getAccount().getAccountNumber();
			return accountDao.fetchAllowAccessFlag(accNum);
		}

		@Override
		public void sendNotification() {
/*//set and push Technical Account //Access notification
			
			Notifications n=new Notifications();
			n.setEmpAdminFlag("C");  // notification to employee
			
			NotificationsType nt= notificationsTypeDAO.fetchNotificationsType("TAA");
			
			n.setNotificationsType(nt);
			
			
			notificationsDAO.insertNotification("Employee",n);*/
			
		}





		
		

}
