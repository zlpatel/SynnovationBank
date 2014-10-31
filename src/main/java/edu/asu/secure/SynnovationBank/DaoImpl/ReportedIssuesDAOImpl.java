package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.ReportedIssues;
import edu.asu.secure.SynnovationBank.Dao.ReportedIssuesDAO;

public class ReportedIssuesDAOImpl implements ReportedIssuesDAO {

	SessionFactory factory = HibernateUtil.buildSessionFactory();

	@Override
	public long insertIssue(ReportedIssues issue) {
		long issueId = -1;
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			issueId = (Long)session.save(issue);
			session.getTransaction().commit();
			return issueId;
		}
		catch(Exception e){
			e.printStackTrace();
			return issueId;
		}
	}

	@Override
	public boolean updateIssueResolved(ReportedIssues issue) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(issue);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
}
