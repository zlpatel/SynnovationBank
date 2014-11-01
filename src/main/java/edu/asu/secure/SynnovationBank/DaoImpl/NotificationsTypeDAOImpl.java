package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.Dao.NotificationsTypeDAO;

@Repository
public class NotificationsTypeDAOImpl implements NotificationsTypeDAO {

	SessionFactory factory = HibernateUtil.buildSessionFactory();
	
	@Override
	public long insertNotificationType(NotificationsType notificationType) {
		long notificationTypeId = -1;
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			notificationTypeId = (Long)session.save(notificationType);
			session.getTransaction().commit();
			return notificationTypeId;
		}
		catch(Exception e){
			e.printStackTrace();
			return notificationTypeId;
		}
	}

}
