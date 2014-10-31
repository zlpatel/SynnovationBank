package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.Dao.TransactionTypeDAO;

public class TransactionTypeDAOImpl implements TransactionTypeDAO {

	SessionFactory factory = HibernateUtil.buildSessionFactory();

	@Override
	public long insertTransactionType(TransactionType transactionType) {
		long transactionTypeId = -1;
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			transactionTypeId = (Long)session.save(transactionType);
			session.getTransaction().commit();
			return transactionTypeId;
		}
		catch(Exception e){
			e.printStackTrace();
			return transactionTypeId;
		}
	}
	
	
}
