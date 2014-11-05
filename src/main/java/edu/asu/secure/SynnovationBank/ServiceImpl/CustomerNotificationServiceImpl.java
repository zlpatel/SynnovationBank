package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
import edu.asu.secure.SynnovationBank.FormBean.CustNotifFormBean;
import edu.asu.secure.SynnovationBank.Service.CustomerNotificationService;
@Service
@Transactional

public class CustomerNotificationServiceImpl implements CustomerNotificationService{

	@Autowired
	private NotificationsDAO notificationsDAO; 
	
	@Override
		    public List<CustNotifFormBean> notifications(String userName) {
		    	 
		CustNotifFormBean empNotifFormBean=null;
		    	
				//System.out.println("Hi it is here!");
		    	List<Notifications> notification=notificationsDAO.fetchNotifications(userName);
		    	//System.out.println("Hi it is here 2!");
		    	List<CustNotifFormBean> list=new ArrayList<CustNotifFormBean>();
		    	
		    	for(Notifications notif: notification){
		    		empNotifFormBean = new CustNotifFormBean();
		    		
		    		empNotifFormBean.setFirstName(notif.getPerson().getFirstName());
		    		empNotifFormBean.setLastName(notif.getPerson().getLastName());
		    		empNotifFormBean.setUserName(notif.getPerson().getUserId());
		    		empNotifFormBean.setNotifications(notif.getNotificationsType().getDescription());
		    		list.add(empNotifFormBean);
		    	}
		    	return list;
		         
		    }

}
