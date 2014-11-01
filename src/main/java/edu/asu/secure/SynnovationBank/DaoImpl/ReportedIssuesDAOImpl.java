package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.ReportedIssues;
import edu.asu.secure.SynnovationBank.Dao.ReportedIssuesDAO;

@Repository
public class ReportedIssuesDAOImpl implements ReportedIssuesDAO {

	SessionFactory factory = HibernateUtil.buildSessionFactory();

	@Override
	public long insertIssue(String userId, ReportedIssues issue) {
		long issueId = -1;
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
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
			session.getTransaction().commit();
			return issueId;
		}
		catch(Exception e){
			e.printStackTrace();
			return issueId;
		}
	}

	@Override
	public boolean updateIssueResolved(Long issueId) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			ReportedIssues issue = (ReportedIssues)session.get(ReportedIssues.class, issueId);
			if(issue != null)
				issue.setResolvedFlag("Y");
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
