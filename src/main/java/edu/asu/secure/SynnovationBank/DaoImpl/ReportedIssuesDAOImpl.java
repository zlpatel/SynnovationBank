package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.ReportedIssues;
import edu.asu.secure.SynnovationBank.Dao.ReportedIssuesDAO;

@Repository
public class ReportedIssuesDAOImpl implements ReportedIssuesDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public long insertIssue(String userId, ReportedIssues issue) {
		Session session = null;
		long issueId = -1;
		try{
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null){
				if(person.getIssues() != null){
					Set<ReportedIssues> set = person.getIssues();
					set.add(issue);
					person.setIssues(set);
				}
				else{
					Set<ReportedIssues> set = new HashSet<ReportedIssues>();
					set.add(issue);
					person.setIssues(set);
				}
				issue.setPerson(person);
			}
			session.update(person);
			return issueId;
		}
		catch(Exception e){
			e.printStackTrace();
			return issueId;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public boolean updateIssueResolved(Long issueId) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			ReportedIssues issue = (ReportedIssues)session.get(ReportedIssues.class, issueId);
			if(issue != null)
				issue.setResolvedFlag("Y");
			session.update(issue);
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
