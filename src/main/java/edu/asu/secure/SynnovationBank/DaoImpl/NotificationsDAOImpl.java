package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;

@Repository
public class NotificationsDAOImpl implements NotificationsDAO {

	@Autowired
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
	public boolean updateResolveNotification(Long notificationId, Person person) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Notifications notifications = (Notifications)session.get(Notifications.class, notificationId);
			if(notifications != null){
				notifications.setPerson(person);
				notifications.setResolvedFlag("Y");
			}
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
		List<Notifications> list = new ArrayList<Notifications>();
		@SuppressWarnings("rawtypes")
		List rawList = null;
		try{
			Session session = factory.openSession();
			Criteria criteria = session.createCriteria(Notifications.class);
			criteria.add(Restrictions.eq("empAdminFlag",empOrAdmin));
			rawList = criteria.list();
			@SuppressWarnings("rawtypes")
			Iterator itr = rawList.iterator();
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
