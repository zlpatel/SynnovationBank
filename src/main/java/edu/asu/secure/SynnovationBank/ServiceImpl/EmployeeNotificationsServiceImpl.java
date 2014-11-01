package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
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
	    	List<Notifications> notification=notificationsDAO.fetchNotifications("E");
	    	List<EmpNotifFormBean> list=new ArrayList<EmpNotifFormBean>();
	    	
	    	for(Notifications notif: notification){
	    		empNotifFormBean = new EmpNotifFormBean();
	    		
	    		empNotifFormBean.setFirstname(notif.getPerson().getFirstName());
	    		empNotifFormBean.setLastName(notif.getPerson().getLastName());
	    		empNotifFormBean.setNotifications(notif.getNotificationsType());
	    		list.add(empNotifFormBean);
	    	}
	    	return list;
	         
	    }
}
