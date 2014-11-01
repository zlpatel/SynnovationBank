package edu.asu.secure.SynnovationBank.Dao;

import java.util.List;

import edu.asu.secure.SynnovationBank.DTO.Notifications;

public interface NotificationsDAO {

	public long insertNotification(Notifications notifications);
	public boolean updateResolveNotification(Notifications notifications);
	public List<Notifications> fetchNotifications(String empOrAdmin);
	public Notifications fetchByNotificationId(Long notificationId);
}
