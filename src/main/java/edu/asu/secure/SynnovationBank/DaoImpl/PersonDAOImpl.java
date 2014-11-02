package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;

@Repository
public class PersonDAOImpl implements PersonDAO {

	SessionFactory factory = HibernateUtil.buildSessionFactory();
	
	@Override
	public boolean insertUser(Person person) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(person);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean insertAccount(String userId, Account account) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null)
				person.setAccount(account);
			session.update(person);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateOTP(String userId, String otp) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, 10);
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null){
				person.setOneTimePassword(otp);
				person.setOtpExpiry(cal.getTime());
			}
			session.update(person);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUserDetails(String userId, String fname, String mname, String lname, String email, String address) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null){
				if(fname!=null) person.setFirstName(fname);
				if(mname!=null) person.setMiddleName(mname);
				if(lname!=null) person.setLastName(lname);
				if(email!=null) person.setEmail(email);
				if(address!=null) person.setAddress(address);
			}
			session.update(person);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePassword(String userId, String password) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null)
				person.setPassword(password);
			session.update(person);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateAccessFlag(String userId, String accessFlag) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null)
				person.setAllowAccessFlag(accessFlag);
			session.update(person);
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean authenticateOTP(String userId, String email, String otp) {
		try{
			Calendar cal = Calendar.getInstance();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Person person = (Person)session.get(Person.class, userId);
			session.getTransaction().commit();
			if(person == null)
				return false;
			else if(person.getEmail().equals(email) && person.getOneTimePassword().equals(otp) && (person.getOtpExpiry().compareTo(cal.getTime())>=0) )
				return true;
			
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	
	@Override
	public boolean authenticateUser(String userId, String password) {
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Person person = (Person)session.get(Person.class, userId);
			session.getTransaction().commit();
			if(person != null && person.getPassword().equals(password))
				return false;
			else
				return false;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Person fetchUserById(String userId) {
		Person person = null;
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			person = (Person)session.get(Person.class, userId);
			return person;
		}
		catch(Exception e){
			e.printStackTrace();
			return person;
		}
	}

	@Override
	public List<Person> fetchUserByRole(String rolename) {
		List<Person> listPerson = new ArrayList<Person>();
		try{
			Session session = factory.openSession();
			Criteria criteria = session.createCriteria(Person.class);
			criteria.add(Restrictions.eq("role",rolename));
			@SuppressWarnings("rawtypes")
			List rawList = criteria.list();
			@SuppressWarnings("rawtypes")
			Iterator itr = rawList.iterator();
			while(itr.hasNext())
				listPerson.add((Person)itr.next());
			return listPerson;
		}
		catch(Exception e){
			e.printStackTrace();
			return listPerson;
		}
	}

	
}
