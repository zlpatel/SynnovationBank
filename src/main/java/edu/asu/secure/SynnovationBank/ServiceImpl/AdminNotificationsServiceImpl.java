package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.Transactions;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsTypeDAO;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionDetailsDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionsDAO;
import edu.asu.secure.SynnovationBank.FormBean.AdminCriticalTransactionsFormBean;
import edu.asu.secure.SynnovationBank.Service.AdminNotificationsService;

@Service
@Transactional
public class AdminNotificationsServiceImpl implements AdminNotificationsService  {
	
	
	   
	    @Autowired
	    private NotificationsDAO notificationsDAO;
	    @Autowired
	    private NotificationsTypeDAO notificationsTypeDAO;
	    @Autowired
	    private PersonDAO personDAO;
	    @Autowired
	    private AccountDAO accountDAO;
	    @Autowired
	    private TransactionsDAO transactionDAO;
	    
	 
	    @Override
	    public List<AdminCriticalTransactionsFormBean> notifications() {
	    	 
	    	AdminCriticalTransactionsFormBean adminNotifFormBean=null;
	    	//System.out.println("Hi it is here!");
	    	List<Notifications> notification=notificationsDAO.fetchNotifications("A");
	    	//System.out.println("Hi it is here 2!");
	    	List<AdminCriticalTransactionsFormBean> list=new ArrayList<AdminCriticalTransactionsFormBean>();
	    	
	    	for(Notifications notif: notification){
	    		adminNotifFormBean = new AdminCriticalTransactionsFormBean();
	    		
	    		//from notifications table
	    		//get username
	    		//account number from person table
	    		//transaction id
	    		
	    		adminNotifFormBean.setFirstName(notif.getPerson().getFirstName());
	    		adminNotifFormBean.setLastName(notif.getPerson().getLastName());
	    		adminNotifFormBean.setUserName(notif.getPerson().getUserId());
	    		adminNotifFormBean.setNotifications(notif.getNotificationsType().getDescription());
	    		adminNotifFormBean.setAccountNumber(notif.getPerson().getAccount().getAccountNumber());
	    		adminNotifFormBean.setTransactionAmount(notif.getTransaction().getAmount());

	    		list.add(adminNotifFormBean);
	    	}
	    	return list;
	         
	    }
	    
	    @Override
	    public void sendTransactionDeclinedNotification(String userName) {
	    // TODO Auto-generated method stub

	    //set and push Technical Account //Access notification
	    //Transactions t=new Transactions();
	    System.out.println("The username in service layer " + userName);
	    Notifications n=new Notifications();
	    n.setEmpAdminFlag(userName); // notification to customer

	    NotificationsType nt= notificationsTypeDAO.fetchNotificationsType("TD");
	    //t.setTransactionId(1L);
	    //n.setTransaction(t);
	    n.setNotificationsType(nt);
	    //send userId as well in this method
	    n.setResolvedFlag("Y");
	    System.out.println("You are in the service impl");
	    notificationsDAO.insertNotification("admin",n);
}
	    
	    @Override
	    public void sendTransactionAcceptedNotification(AdminCriticalTransactionsFormBean criticalTransactionFormBean)
	    {
	    	
	    	//send notification to sender that transaction has been accepted
	    	//notification type set as "CT" --> add new type in DB
	    	
	    	
	    	//update balance
	    	
	    	
	    	
	    	System.out.println("The username in service layer " + criticalTransactionFormBean.getUserName());
		    Notifications n=new Notifications();
		    n.setEmpAdminFlag(criticalTransactionFormBean.getUserName()); // notification to customer

		    NotificationsType nt= notificationsTypeDAO.fetchNotificationsType("TA");
		    //t.setTransactionId(1L);
		    //n.setTransaction(t);
		    n.setNotificationsType(nt);
		    //send userId as well in this method
		    n.setResolvedFlag("Y");
		    notificationsDAO.insertNotification("admin",n);
	 
		    
		    
	    	Person sender = personDAO.fetchUserById(criticalTransactionFormBean.getUserName());
			Account a=(Account) sender.getAccount();
			float balance=a.getBalance();
			float debit=criticalTransactionFormBean.getTransactionAmount();
			float new_balance=balance-debit;
			a.setBalance(new_balance);
			
	    	//call method to get receiver's account number --> jeffrey
			
			//get Account from account number --> jeffrey
			
			Account b=(Account) receiver.getAccount();
			float balanceB=b.getBalance();
			float credit=criticalTransactionFormBean.getTransactionAmount();
			float new_balanceB=balanceB+credit;
			b.setBalance(new_balanceB);
			
			
			System.out.println("***************************************************");
			System.out.println("PERFORMING DEBIT FROM YOUR ACCOUNT AND CREDITING TO THE OTHER ACCOUNT!");
			System.out.println("***************************************************");
			
			
			accountDAO.updateAccountBalance(a.getAccountNumber(), a.getBalance());
			System.out.println("Debitted sender account table");
			
			
			accountDAO.updateAccountBalance(b.getAccountNumber(), b.getBalance());
			System.out.println("Creddited receiver account table");
			
			//get transaction from transaction id
			long transactionId = criticalTransactionFormBean.getTransactionId();
			Transactions transactions =  transactionDAO.fetchTransactionById(transactionId);
			
	    	//set transaction status to C from P
			//transactions.setStatus("C");
			
			
			
	    }
	    
	    }
