package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.FormBean.EmpNotifFormBean;
import edu.asu.secure.SynnovationBank.Service.EmployeeNotificationsService;

@Service
@Transactional
public class EmployeeNotificationsServiceImpl implements EmployeeNotificationsService  {
	
	
	   
	    @Autowired
	    private NotificationsDAO notificationsDAO;
	     
	    
	 
	    @Transactional
	    public List<EmpNotifFormBean> notifications() {
	    	 
	    	EmpNotifFormBean empNotifFormBean=null;
	    	List<Notifications> notification=NotificationsDAO.listOfNotifications();
	    	List<EmpNotifFormBean> list=new ArrayList<EmpNotifFormBean>();
	    	
	    	for(Notifications notif: notification){
	    		empNotifFormBean = new EmpNotifFormBean();
	    		
	    		empNotifFormBean.setFirstname(notif.getFirstname());
	    		empNotifFormBean.setLastName(notif.getLastname());
	    		empNotifFormBean.setNotifications(notif.getNotifications);
	    		list.add(empNotifFormBean);
	    	}
	    	return list;
	         
	    }
}
