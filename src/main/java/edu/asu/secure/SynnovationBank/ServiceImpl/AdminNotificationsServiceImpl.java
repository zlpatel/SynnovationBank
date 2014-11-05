package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
import edu.asu.secure.SynnovationBank.FormBean.AdminCriticalTransactionsFormBean;
import edu.asu.secure.SynnovationBank.Service.AdminNotificationsService;

@Service
@Transactional
public class AdminNotificationsServiceImpl implements AdminNotificationsService  {
	
	
	   
	    @Autowired
	    private NotificationsDAO notificationsDAO;
	     
	    
	 
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
	    

	  /*  @Override
	    public List<AdminCriticalTransactionsFormBean> merchantRequests() {
	    	 
	    	AdminCriticalTransactionsFormBean adminNotifFormBean=null;
	    	//System.out.println("Hi it is here!");
	    	List<Notifications> notification=notificationsDAO.fetchNotifications("E");
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
	         
	    }*/
}
