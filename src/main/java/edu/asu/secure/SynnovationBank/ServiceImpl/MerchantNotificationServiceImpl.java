package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsTypeDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionsDAO;
import edu.asu.secure.SynnovationBank.FormBean.MerchantNotifFormBean;
import edu.asu.secure.SynnovationBank.FormBean.MerchantPaymentFormBean;
import edu.asu.secure.SynnovationBank.FormBean.MerchantTransactionFormBean;
import edu.asu.secure.SynnovationBank.Service.MerchantNotificationService;
@Service
@Transactional

public class MerchantNotificationServiceImpl implements MerchantNotificationService{

	@Autowired
	private NotificationsDAO notificationsDAO; 
	@Autowired
	private TransactionsDAO transactionsDAO;
	@Autowired
	private NotificationsTypeDAO notificationsTypeDAO; 
	
	@Override
	public List<MerchantNotifFormBean> notifications(String userName) {
		    	 
		MerchantNotifFormBean mercNotifFormBean=null;
		MerchantTransactionFormBean mercTransactionFormBean=null;    	
				System.out.println("Hi it is here!");
				List<Notifications> notification=notificationsDAO.fetchNotifications(userName, 1 ,"N");
		    	System.out.println(notification.size());
		    	
		    	List<MerchantNotifFormBean> list=new ArrayList<MerchantNotifFormBean>();
		    	
		    	for(Notifications notif: notification){
		    		
		    		mercNotifFormBean = new MerchantNotifFormBean();
		    	  
		    		   
		    		mercNotifFormBean.setNotification_id(notif.getNotificationId());
		    		mercNotifFormBean.setAmount(notif.getTransaction().getAmount());
		    		mercNotifFormBean.setFirstName(notif.getPerson().getFirstName());
		    		mercNotifFormBean.setLastName(notif.getPerson().getLastName());
		    		mercNotifFormBean.setUserName(notif.getPerson().getUserId());
		    		mercNotifFormBean.setNotifications(notif.getNotificationsType().getDescription());
		            list.add(mercNotifFormBean); 	   
		    	 
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
		    notificationsDAO.insertNotification("emp",n);
		  }
			
		
	    @Override
		 public void submitPayment(String userName)
			{
			    	
			    	//send notification to sender that transaction has been accepted
			    	//notification type set as "CT" --> add new type in DB
			    	
			    	//System.out.println("The username in service layer " + criticalTransactionFormBean.getUserName());
//				    Notifications n=new Notifications();
//				    n.setEmpAdminFlag(criticalTransactionFormBean.getUserName()); // notification to customer
//
//				    NotificationsType nt= notificationsTypeDAO.fetchNotificationsType("TA");
//				    //t.setTransactionId(1L);
//				    //n.setTransaction(t);
//				    n.setNotificationsType(nt);
//				    //send userId as well in this method
//				    n.setResolvedFlag("Y");
//				    notificationsDAO.insertNotification("emp",n);
//			 
//				    
//				    
//			    	Person sender = personDAO.fetchUserById(criticalTransactionFormBean.getUserName());
//					Account a=(Account) sender.getAccount();
//					float balance=a.getBalance();
//					float debit=criticalTransactionFormBean.getTransactionAmount();
//					float new_balance=balance-debit;
//					a.setBalance(new_balance);
//					
//			    	//get receiver's account number
//					long receiverAccountNo =  transactionDAO.fetchCreditorAccountNo(criticalTransactionFormBean.getTransactionId());
//					
//					//get Account from account number
//					
//					Account b=(Account) accountDAO.fetchAccountByNumber(receiverAccountNo);
//					float balanceB=b.getBalance();
//					float credit=criticalTransactionFormBean.getTransactionAmount();
//					float new_balanceB=balanceB+credit;
//					b.setBalance(new_balanceB);
//					
//					System.out.println("***************************************************");
//					System.out.println("PERFORMING DEBIT FROM YOUR ACCOUNT AND CREDITING TO THE OTHER ACCOUNT!");
//					System.out.println("***************************************************");
//					
//					accountDAO.updateAccountBalance(a.getAccountNumber(), a.getBalance());
//					System.out.println("Debitted sender account table");
//					
//					accountDAO.updateAccountBalance(b.getAccountNumber(), b.getBalance());
//					System.out.println("Creddited receiver account table");
//					
//					//get transaction from transaction id
//					long transactionId = criticalTransactionFormBean.getTransactionId();
//					Transactions transactions =  transactionDAO.fetchTransactionById(transactionId);
//					
//			    	//set transaction status to C from P
//					//transactions.setStatus("C");
//					
//					
	    	      	
					
		}	
	
	    
	    @Override
	    public Notifications getNotificationsById(long notifId){
	    	
	    	Notifications notifObj=notificationsDAO.fetchByNotificationId(notifId);
	    	return notifObj;
	    	
	    	
	    	
	    }
	    
	    @Override
	    public MerchantPaymentFormBean getPaymentNotifications(Notifications notifObj){
	    	
	    	MerchantPaymentFormBean merchantPaymentFormBean=new MerchantPaymentFormBean();
	    	merchantPaymentFormBean.setAmount(notifObj.getTransaction().getAmount());
	    	merchantPaymentFormBean.setEmp_admin_flag("E");
	    	//merchantPaymentFormBean.setFile(file);
	    	merchantPaymentFormBean.setNotification_id(notifObj.getNotificationId());
	    	merchantPaymentFormBean.setNotifications(notifObj.getNotificationsType().getDescription());
	    	merchantPaymentFormBean.setResolved_flag("Y");
	    	merchantPaymentFormBean.setTransaction_id(notifObj.getTransaction().getTransactionId());
	    	
	    	return merchantPaymentFormBean;
	    	
	    	
	    }
	    
	    @Override
	    public boolean submitPayment(MerchantPaymentFormBean merchantPaymentFormBean){
	    	//notificationsDAO.updateResolveNotification(notificationId, person)
	    	
	    	 
	    	
	    }
	    

}
