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
import edu.asu.secure.SynnovationBank.FormBean.AdminPIIRequestsFormBean;
import edu.asu.secure.SynnovationBank.FormBean.ExternalUserFormBean;
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
	    public List<AdminCriticalTransactionsFormBean> getCriticalTransactionNotifications() {
	    	 
	    	AdminCriticalTransactionsFormBean adminNotifFormBean=null;
	    	//System.out.println("Hi it is here!");
	    	List<Notifications> notification=notificationsDAO.fetchNotifications("A", 1 ,"N");
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
	    		adminNotifFormBean.setTransactionId(notif.getTransaction().getTransactionId());
	    		adminNotifFormBean.setNotificationId(notif.getNotificationId());

	    		list.add(adminNotifFormBean);
	    	}
	    	return list;
	         
	    }
	    
	    @Override
	    public void sendTransactionDeclinedNotification(String userId, long tId,long nId) {
	    // TODO Auto-generated method stub
	    	
	    //send new notification to user	
	    System.out.println("The username in service layer " + userId);
	    Notifications n=new Notifications();
	    n.setEmpAdminFlag(userId); // notification to customer

	    NotificationsType nt= notificationsTypeDAO.fetchNotificationsType("TD");
	    n.setNotificationsType(nt);
	    //send userId as well in this method
	    n.setResolvedFlag("Y");
	    System.out.println("You are in the service impl");
	    notificationsDAO.insertNotification("admin",n);
	    
	    //update this CT notification. set resolved.
	    Person person = personDAO.fetchUserById(userId);
	    notificationsDAO.updateResolveNotification(nId, person);	
	    
	  //get transaction from transaction id
		Transactions transactions =  transactionDAO.fetchTransactionById(tId);
		transactions.setCompleteFlag("D");		
}
	    
	    @Override
	    public void sendTransactionAcceptedNotification(String userId, long tId, long nId)
	    {
	    	System.out.println(tId);
//	    	long tId = Long.valueOf(transactionId).longValue();
	    	Transactions t = new Transactions();
	    	t = transactionDAO.fetchTransactionById(tId);
	    	
	    	
	    	//send notification to sender that transaction has been accepted
	    	//notification type set as "CT" --> add new type in DB
	    	
	    	
	    	//update balance
	    	
	    	
	    	
	    	System.out.println("The username in service layer " + userId);
		    Notifications n=new Notifications();
		    n.setEmpAdminFlag(userId); // notification to customer

		    NotificationsType nt= notificationsTypeDAO.fetchNotificationsType("TA");
		    //t.setTransactionId(1L);
		    //n.setTransaction(t);
		    n.setNotificationsType(nt);
		    //send userId as well in this method
		    n.setResolvedFlag("Y");
		    notificationsDAO.insertNotification("admin",n);
	 
		
		    
	    	Person sender = personDAO.fetchUserById(userId);
			Account a=(Account) sender.getAccount();
			float balance=a.getBalance();
			float debit=t.getAmount();
			float new_balance=balance-debit;
			a.setBalance(new_balance);
			
	    	//get receiver's account number
			long receiverAccountNo =  transactionDAO.fetchCreditorAccountNo(tId);
			
			//get Account from account number
			
			Account b=(Account) accountDAO.fetchAccountByNumber(receiverAccountNo);
			float balanceB=b.getBalance();
			float credit=t.getAmount();
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
			Transactions transactions =  transactionDAO.fetchTransactionById(tId);
			transactions.setCompleteFlag("C");
			
		    Person person = personDAO.fetchUserById(userId);
		    notificationsDAO.updateResolveNotification(nId, person);	    
			
	    }

		@Override
		public List<AdminPIIRequestsFormBean> getPIIRequestNotifications() {

			AdminPIIRequestsFormBean adminPIIRequestsFormBean=null;
	    	List<Notifications> notification=notificationsDAO.fetchNotifications("A", 5 ,"N");
	    	List<AdminPIIRequestsFormBean> list=new ArrayList<AdminPIIRequestsFormBean>();
	    	
	    	for(Notifications notif: notification){
	    		
	    		adminPIIRequestsFormBean = new AdminPIIRequestsFormBean();
	    		
	    		adminPIIRequestsFormBean.setFirstName(notif.getPerson().getFirstName());
	    		adminPIIRequestsFormBean.setLastName(notif.getPerson().getLastName());
	    		adminPIIRequestsFormBean.setUserName(notif.getPerson().getUserId());
	    		adminPIIRequestsFormBean.setAccountNumber(notif.getPerson().getAccount().getAccountNumber());
	    		adminPIIRequestsFormBean.setDateOfBirth(notif.getPerson().getDateOfBirth().toString());
	    		adminPIIRequestsFormBean.setSsn(notif.getPerson().getSsn());

	    		list.add(adminPIIRequestsFormBean);
	    	}
	    	return list;		}

		@Override
		public void addPIIRequestNotification(
				ExternalUserFormBean modifyexternaluserformbean) 
		{
			Person person = personDAO.fetchUserById(modifyexternaluserformbean.getUsername());
			if(!person.getPiiRequestFlag())
			//send new notification to Admin	
		    {
				person.setPiiRequestFlag(true);
				System.out.println("The username in service layer " + modifyexternaluserformbean.getUsername());
		    
				Notifications n=new Notifications();
				n.setEmpAdminFlag("A"); // notification to admin

				NotificationsType nt= notificationsTypeDAO.fetchNotificationsType("PII");
				n.setNotificationsType(nt);
				//send userId as well in this method
				n.setResolvedFlag("N");
				notificationsDAO.insertNotification(modifyexternaluserformbean.getUsername(),n);
		    }
		}

	    
	    }
