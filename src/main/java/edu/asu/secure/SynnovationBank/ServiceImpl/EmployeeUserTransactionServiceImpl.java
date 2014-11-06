package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;

import edu.asu.secure.SynnovationBank.Dao.AccountDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsTypeDAO;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionDetailsDAO;


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
		public void sendNotification(String userName) {
			// TODO Auto-generated method stub
		    
		     //set and push Technical Account //Access notification
		     			//Transactions t=new Transactions();
				System.out.println("The username in service layer " + userName);
		     			Notifications n=new Notifications();
		     			n.setEmpAdminFlag(userName);  // notification to customer
		     			
		     			NotificationsType nt= notificationsTypeDAO.fetchNotificationsType("TAA");
		     			//t.setTransactionId(1L);
		     			//n.setTransaction(t);
		     			n.setNotificationsType(nt);
		     			//send userId as well in this method
		     			  n.setResolvedFlag("N");
		     			System.out.println("You are in the service impl");
		     			notificationsDAO.insertNotification("emp",n);
		     			
		     			
			/*Notifications n=new Notifications();
			n.setEmpAdminFlag("E");  // notification to employee
			
			NotificationsType nt= notificationsTypeDAO.fetchNotificationsType("TAA");
			
			n.setNotificationsType(nt);
			
			n.setResolvedFlag("N");
			notificationsDAO.insertNotification(userID,n);
		     			
		     			NotificationsType type = notificationsTypeDAO.fetchNotificationsType("TAA");
		     			
		     			transactions.setTransactionId(1L);
		     			
		     			
		     			notification.setEmpAdminFlag(userName);
		     			notification.setResolvedFlag("N");
		     			notification.setNotificationsType(type);
		     			notification.setTransaction(transactions);
		     			notificationsDAO.insertNotification("Employee", notification);
			*/
		}

}
