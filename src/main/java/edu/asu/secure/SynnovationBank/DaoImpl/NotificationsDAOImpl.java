package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;

public class NotificationsDAOImpl implements NotificationsDAO {

	SessionFactory factory = HibernateUtil.buildSessionFactory();

	@Override
	public long insertNotification(Notifications notifications) {
		long notificationId = -1;
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			notificationId = (Long)session.save(notifications);
			session.getTransaction().commit();
			return notificationId;
		}
		catch(Exception e){
			e.printStackTrace();
			return notificationId;
		}
	}

	@Override
	public boolean updateResolveNotification(Notifications notifications) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(notifications);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Notifications> fetchNotifications(String empOrAdmin) {
		List<Notifications> list = null;
		List absList = null;
		try{
			Session session = factory.getCurrentSession();
			Criteria criteria = session.createCriteria(Notifications.class);
			criteria.add(Restrictions.eq("empAdminFlag",empOrAdmin));
			absList = (List)criteria.list();
			Iterator itr = absList.iterator();
			while(itr.hasNext())
				list.add((Notifications)itr.next());
			return list;
		}
		catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}

	@Override
	public Notifications fetchByNotificationId(Long notificationId) {
		Notifications msg = null;
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			msg = (Notifications)session.get(Notifications.class, notificationId);
			return msg;
		}
		catch(Exception e){
			e.printStackTrace();
			return msg;
		}
	}
	
	
}
