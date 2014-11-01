package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.Dao.TransactionTypeDAO;

@Repository
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

	@Override
	public TransactionType fetchTransactionType(String transactionName) {
		TransactionType transactionType = null;
		try{
			Session session = factory.openSession();
			Criteria criteria = session.createCriteria(TransactionType.class);
			criteria.add(Restrictions.eq("transactionName", transactionName));
			transactionType = (TransactionType)criteria.uniqueResult();
			return transactionType;
		}
		catch(Exception e){
			e.printStackTrace();
			return transactionType;
		}
	}	
	
}
