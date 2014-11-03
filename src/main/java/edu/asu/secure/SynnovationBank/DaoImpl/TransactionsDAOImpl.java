package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DTO.Transactions;
import edu.asu.secure.SynnovationBank.Dao.TransactionsDAO;

@Repository
public class TransactionsDAOImpl implements TransactionsDAO {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public long insertTransaction(Transactions transactions) {
		Session session = null;
		long transactionId = -1;
		try{
			session = factory.getCurrentSession();
			transactionId = (Long)session.save(transactions);
			return transactionId;
		}
		catch(Exception e){
			e.printStackTrace();
			return transactionId;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public Transactions fetchTransactionById(Long transactionId) {
		Session session = null;
		Transactions transactions = null;
		try {
			session = factory.getCurrentSession();
			transactions = (Transactions)session.get(Transactions.class, transactionId);
			return transactions;
		}
		catch(Exception e){
			e.printStackTrace();
			return transactions;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public boolean deleteTransactionById(Long transactionId) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			Transactions transactions = (Transactions)session.get(Transactions.class, transactionId);
			session.delete(transactions);
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

}
