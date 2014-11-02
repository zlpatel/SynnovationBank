package edu.asu.secure.SynnovationBank.Dao;

import java.util.List;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.Person;

public interface NotificationsDAO {

	public boolean insertNotification(String userId, Notifications notifications);
	
	public boolean updateResolveNotification(Long notificationId, Person person);
	
	public List<Notifications> fetchNotifications(String empOrAdmin);
	public Notifications fetchByNotificationId(Long notificationId);

	
}
