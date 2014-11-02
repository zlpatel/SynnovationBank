package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;
import edu.asu.secure.SynnovationBank.Dao.TransactionDetailsDAO;

@Repository
public class TransactionDetailsDAOImpl implements TransactionDetailsDAO {

	SessionFactory factory = HibernateUtil.buildSessionFactory();

	@Override
	public List<TransactionDetails> fetchAccountTransactions(Long accountNo) {
		Session session = null;
		List<TransactionDetails> list = new ArrayList<TransactionDetails>();
		@SuppressWarnings("rawtypes")
		List rawList = null;
		try{
			session = factory.openSession();
			Criteria criteria = session.createCriteria(TransactionDetails.class);
			criteria.createCriteria("account");
			criteria.setFetchMode("account",FetchMode.JOIN);
			criteria.add(Restrictions.eq("account.accountNumber",accountNo));
			criteria.createCriteria("transactions");
			criteria.setFetchMode("transactions",FetchMode.JOIN);
			criteria.createCriteria("transactionType");
			criteria.setFetchMode("transactionType",FetchMode.JOIN);
			rawList = criteria.list();
			@SuppressWarnings("rawtypes")
			Iterator itr = rawList.iterator();
			while(itr.hasNext())
				list.add((TransactionDetails)itr.next());
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
	public List<TransactionDetails> fetchAccountTransactions(Long accountNo, int rowCount) {
		Session session = null;
		List<TransactionDetails> list = new ArrayList<TransactionDetails>();
		@SuppressWarnings("rawtypes")
		List rawList = null;
		try{
			session = factory.openSession();
			Criteria criteria = session.createCriteria(TransactionDetails.class);
			criteria.addOrder(Order.desc("sequenceId"));
			criteria.createCriteria("account");
			criteria.setFetchMode("account",FetchMode.JOIN);
			criteria.add(Restrictions.eq("account.accountNumber",accountNo));
			criteria.createCriteria("transactions");
			criteria.setFetchMode("transactions",FetchMode.JOIN);
			criteria.createCriteria("transactionType");
			criteria.setFetchMode("transactionType",FetchMode.JOIN);
			criteria.setMaxResults(rowCount);
			rawList = criteria.list();
			@SuppressWarnings("rawtypes")
			Iterator itr = rawList.iterator();
			while(itr.hasNext())
				list.add((TransactionDetails)itr.next());
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
	
	
}
