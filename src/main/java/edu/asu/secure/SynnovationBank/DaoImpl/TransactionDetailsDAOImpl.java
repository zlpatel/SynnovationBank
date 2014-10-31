package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;
import edu.asu.secure.SynnovationBank.Dao.TransactionDetailsDAO;

public class TransactionDetailsDAOImpl implements TransactionDetailsDAO {

	SessionFactory factory = HibernateUtil.buildSessionFactory();

	@Override
	public long insertTransactionDetails(TransactionDetails transactionDetails) {
		long seqId = -1;
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			seqId = (Long)session.save(transactionDetails);
			session.getTransaction().commit();
			return seqId;
		}
		catch(Exception e){
			e.printStackTrace();
			return seqId;
		}
	}

	@Override
	public boolean deleteTransactionDetailsById(Long transactionId) {
		try{
			Session session = factory.getCurrentSession();
			String hql = "delete from Transaction_Details where transaction_id= :id";
			session.createQuery(hql).setLong("id", transactionId).executeUpdate();
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<TransactionDetails> fetchLastTenTransactions(Long accountNo) {
		List<TransactionDetails> list = null;
		List absList = null;
		try{
			Session session = factory.getCurrentSession();
			Criteria criteria = session.createCriteria(TransactionDetails.class);
			criteria.add(Restrictions.eq("accountNumber",accountNo));
			criteria.createCriteria("transactions");
			criteria.createCriteria("transactionType");
			criteria.setFetchMode("transactionType",FetchMode.JOIN);
			criteria.addOrder(Order.desc("transactions.date"));
			absList = (List)criteria.list();
			Iterator itr = absList.iterator();
			while(itr.hasNext())
				list.add((TransactionDetails)itr.next());
			return list;
		}
		catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}
	
	
}
