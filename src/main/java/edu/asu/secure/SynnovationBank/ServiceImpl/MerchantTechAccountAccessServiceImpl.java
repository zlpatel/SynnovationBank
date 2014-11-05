package edu.asu.secure.SynnovationBank.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsTypeDAO;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.Service.MerchantTechAccountAccessService;

@Service
@Transactional

public class MerchantTechAccountAccessServiceImpl implements MerchantTechAccountAccessService {

	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private NotificationsTypeDAO notificationsTypeDAO;
	
	@Autowired
	private NotificationsDAO notificationsDAO; 
	
	@Override
	public boolean setAccessFlag(String userID, String flag) {
	
		System.out.println("Inside service layer!");
		
		if(flag=="N")
		{
			System.out.println("**********************************************************");
			System.out.println("User diallowed TAA...so no notifications...direct DB update");
			System.out.println("**********************************************************");
			
				if(personDAO.updateAccessFlag(userID, flag))
					return true;
				else
					return false;
				}
		else
		{
			
			System.out.println("**********************************************************");
			System.out.println("User allowed TAA......generating notifications.....");
			System.out.println("**********************************************************");
			
			
			
			/*
			//set and push Technical Account Access notification
			
			Notifications n=new Notifications();
			n.setEmpAdminFlag("E");  // notification to employee
			
			NotificationsType nt= notificationsTypeDAO.fetchNotificationsType("TAA");
			
			n.setNotificationsType(nt);
			
			
			notificationsDAO.insertNotification(userID,n);*/
			
			return true;
		}
	}
	

}
