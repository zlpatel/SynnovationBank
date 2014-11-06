package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Transactions;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsTypeDAO;
import edu.asu.secure.SynnovationBank.Dao.TransactionsDAO;
import edu.asu.secure.SynnovationBank.FormBean.MerchantNotifFormBean;
import edu.asu.secure.SynnovationBank.FormBean.MerchantPaymentFormBean;
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
				System.out.println("Hi it is here!" +userName);
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
		    		mercNotifFormBean.setTransaction_id(notif.getTransaction().getTransactionId());
		            list.add(mercNotifFormBean); 	   
		    	 
		    	}
		    	return list;
		         
       }

			@Override
		    public void sendTransactionDeclinedNotification(String userName,long notification_id) {
		    // TODO Auto-generated method stub
		
		    //Update flag
		    System.out.println("Transaction declined for user " + userName);
		    Notifications notifications=notificationsDAO.fetchByNotificationId(notification_id);
		    notifications.setResolvedFlag("Y");
		    notificationsDAO.updateNotificationEmpAdminFlag(notification_id,userName);
		   
		  
		    //Insert new notifications
		    Notifications n=new Notifications();
		    
		    n.setEmpAdminFlag(userName); // notification to customer
		
		    NotificationsType nt= notificationsTypeDAO.fetchNotificationsType("TD");
		    n.setNotificationsType(nt);
		    //send userId as well in this method
		    n.setResolvedFlag("Y");
		    System.out.println("Reject notification sent to user"+userName);
		    notificationsDAO.insertNotification("Merchant",n);
		  }
			
			@Override
	        public void sendTransactionAcceptedNotification(long notification_id)
	        {
	            System.out.println(notification_id);
	            
	            //send notification to sender that transaction has been accepted
	            //notification type set as "CT" --> add new type in DB
	            
	            Notifications notifObj=notificationsDAO.fetchByNotificationId(notification_id);
	            notifObj.setEmpAdminFlag("E"); // notification to employee
	            
	            System.out.println("***************************************************");
	            System.out.println("NOTIFICATION TO EMPLOYEE SENT!");
	            System.out.println("***************************************************");
	    
	        }
			
	    
	    @Override
	    public Notifications getNotificationsById(long notifId){
	    	
	    	Notifications notifObj=notificationsDAO.fetchByNotificationId(notifId);
	    	return notifObj;
	    	
	    	
	    	
	    }
	    
	   @Override
	    public float getTransactionAmount(long transaction_id){
	    	Transactions transactions=transactionsDAO.fetchTransactionById(transaction_id);
	    	return transactions.getAmount();
	    }
	    

}
