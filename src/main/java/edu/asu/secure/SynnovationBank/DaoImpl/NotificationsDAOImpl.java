package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;

@Repository
public class NotificationsDAOImpl implements NotificationsDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public long insertNotification(String userId, Notifications notifications) {
		Session session = null;
		long issueId = -1;
		try{
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null){
				if(person.getNotifications() != null){
					Set<Notifications> set = person.getNotifications();
					set.add(notifications);
					person.setNotifications(set);
				}
				else{
					Set<Notifications> set = new HashSet<Notifications>();
					set.add(notifications);
					person.setNotifications(set);
				}
				person.setAllowAccessFlag("Y");
				notifications.setPerson(person);
			}
			session.update(person);
			return issueId;
		}
		catch(Exception e){
			e.printStackTrace();
			return issueId;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public boolean updateResolveNotification(Long notificationId, Person person) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			Notifications notifications = (Notifications)session.get(Notifications.class, notificationId);
			if(notifications != null){
				notifications.setPerson(person);
				notifications.setResolvedFlag("Y");
			}
			session.update(notifications);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public List<Notifications> fetchNotifications(String empOrAdmin) {
		Session session = null;
		List<Notifications> list = new ArrayList<Notifications>();
		@SuppressWarnings("rawtypes")
		List rawList = null;
		try{
			session = factory.getCurrentSession();
			Criteria criteria = session.createCriteria(Notifications.class);
			criteria.add(Restrictions.eq("empAdminFlag",empOrAdmin));
			criteria.createCriteria("person");
			criteria.setFetchMode("person",FetchMode.JOIN);
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
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public List<Notifications> fetchNotifications(String empOrAdmin, long notificationTypeId) {
		Session session = null;
		List<Notifications> list = new ArrayList<Notifications>();
		@SuppressWarnings("rawtypes")
		List rawList = null;
		try{
			session = factory.getCurrentSession();
			Criteria criteria = session.createCriteria(Notifications.class);
			criteria.add(Restrictions.eq("empAdminFlag",empOrAdmin));
			criteria.createCriteria("person");
			criteria.setFetchMode("person",FetchMode.JOIN);
			criteria.createCriteria("notificationsType");
			criteria.setFetchMode("notificationsType",FetchMode.JOIN);
			criteria.add(Restrictions.eq("notificationsType.notificationTypeId", notificationTypeId));
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
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public Notifications fetchByNotificationId(Long notificationId) {
		Session session = null;
		Notifications msg = null;
		try {
			session = factory.getCurrentSession();
			msg = (Notifications)session.get(Notifications.class, notificationId);
			return msg;
		}
		catch(Exception e){
			e.printStackTrace();
			return msg;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}
	
	
}
