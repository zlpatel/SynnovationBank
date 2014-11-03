package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.Dao.NotificationsTypeDAO;

@Repository
public class NotificationsTypeDAOImpl implements NotificationsTypeDAO {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public long insertNotificationType(NotificationsType notificationType) {
		Session session = null;
		long notificationTypeId = -1;
		try{
			session = factory.getCurrentSession();
			notificationTypeId = (Long)session.save(notificationType);
			return notificationTypeId;
		}
		catch(Exception e){
			e.printStackTrace();
			return notificationTypeId;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public NotificationsType fetchNotificationsType(String notificationType) {
		Session session = null;
		NotificationsType notificationsType = null;
		try{
			session = factory.getCurrentSession();
			Criteria criteria = session.createCriteria(NotificationsType.class);
			criteria.add(Restrictions.eq("notificationType", notificationType));
			notificationsType = (NotificationsType)criteria.uniqueResult();
			return notificationsType;
		}
		catch(Exception e){
			e.printStackTrace();
			return notificationsType;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

}
