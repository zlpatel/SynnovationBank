package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.Dao.TransactionTypeDAO;

@Repository
public class TransactionTypeDAOImpl implements TransactionTypeDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public long insertTransactionType(TransactionType transactionType) {
		Session session = null;
		long transactionTypeId = -1;
		try{
			session = factory.getCurrentSession();
			transactionTypeId = (Long)session.save(transactionType);
			return transactionTypeId;
		}
		catch(Exception e){
			e.printStackTrace();
			return transactionTypeId;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public TransactionType fetchTransactionType(String transactionName) {
		Session session = null;
		TransactionType transactionType = null;
		try{
			session = factory.getCurrentSession();
			Criteria criteria = session.createCriteria(TransactionType.class);
			criteria.add(Restrictions.eq("transactionName", transactionName));
			transactionType = (TransactionType)criteria.uniqueResult();
			return transactionType;
		}
		catch(Exception e){
			e.printStackTrace();
			return transactionType;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}	
	
}
