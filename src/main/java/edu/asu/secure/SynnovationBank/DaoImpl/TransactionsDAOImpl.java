package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DTO.Account;
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
	public boolean updateTransactionCompleteFlag(Long transactionId, String completeFlag) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			Transactions transaction = (Transactions)session.get(Transactions.class, transactionId);
			if(transaction != null)
				transaction.setCompleteFlag(completeFlag);
			session.update(transaction);
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
	public long fetchCreditorAccountNo(Long transactionId) {
		Session session = null;
		Long creditorAccount = -1L;
		try{
			Account account = null;
            session = factory.getCurrentSession();
            Query query = session.createQuery("SELECT A FROM TransactionDetails as TD JOIN TD.transactionType as TT JOIN TD.account as A JOIN TD.transactions as T"
            + " WHERE TD.transactionType = TT AND TD.account = A AND TD.transactions = T AND TT.transactionName = 'CREDIT' AND T.transactionId = :transactionId");
            query.setLong("transactionId", transactionId);
            account = (Account)query.uniqueResult();
            creditorAccount = account.getAccountNumber();
            return creditorAccount;
		}
		catch(Exception e){
			e.printStackTrace();
			return creditorAccount;
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
