package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.Dao.NotificationsTypeDAO;

@Repository
public class NotificationsTypeDAOImpl implements NotificationsTypeDAO {

	SessionFactory factory = HibernateUtil.buildSessionFactory();
	
	@Override
	public long insertNotificationType(NotificationsType notificationType) {
		Session session = null;
		long notificationTypeId = -1;
		try{
			session = factory.getCurrentSession();
			session.beginTransaction();
			notificationTypeId = (Long)session.save(notificationType);
			session.getTransaction().commit();
			return notificationTypeId;
		}
		catch(Exception e){
			session.getTransaction().rollback();
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
			session = factory.openSession();
			Criteria criteria = session.createCriteria(NotificationsType.class);
			criteria.add(Restrictions.eq("notificationType", notificationType));
			notificationsType = (NotificationsType)criteria.uniqueResult();
			session.getTransaction().commit();
			return notificationsType;
		}
		catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
			return notificationsType;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

}
