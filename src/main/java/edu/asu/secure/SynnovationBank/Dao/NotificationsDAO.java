package edu.asu.secure.SynnovationBank.Dao;

import java.util.List;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.Person;

public interface NotificationsDAO {

	public long insertNotification(String userId, Notifications notifications);
	
	public boolean updateResolveNotification(Long notificationId, Person person);
	public boolean updateNotificationEmpAdminFlag(Long notificationId, String empOrAdmin);
	
	public List<Notifications> fetchNotifications(String empOrAdmin);
	public List<Notifications> fetchNotifications(String empOrAdmin, String resolvedFlag);
	public List<Notifications> fetchNotifications(String empOrAdmin, long notificationTypeId, String resolvedFlag);
	public Notifications fetchByNotificationId(Long notificationId);

	
}
