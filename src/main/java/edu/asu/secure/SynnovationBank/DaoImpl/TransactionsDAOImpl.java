package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.asu.secure.SynnovationBank.DTO.Transactions;
import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.Dao.TransactionsDAO;

public class TransactionsDAOImpl implements TransactionsDAO {

	SessionFactory factory = HibernateUtil.buildSessionFactory();
	
	@Override
	public long insertTransaction(Transactions transactions) {
		long transactionId = -1;
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			transactionId = (Long)session.save(transactions);
			session.getTransaction().commit();
			return transactionId;
		}
		catch(Exception e){
			e.printStackTrace();
			return transactionId;
		}
	}

	@Override
	public boolean deleteTransactionById(Long transactionId) {
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Transactions transactions = (Transactions)session.get(Transactions.class, transactionId);
			session.delete(transactions);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

}
