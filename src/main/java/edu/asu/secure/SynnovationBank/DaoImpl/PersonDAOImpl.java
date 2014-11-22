package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;

@Repository
public class PersonDAOImpl implements PersonDAO {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public boolean insertUser(Person person) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			session.save(person);
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
	public boolean insertAccount(String userId, Account account) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null)
				person.setAccount(account);
			session.update(person);
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
	public boolean updateOTP(String userId, String email, String otp) {
		Session session = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, 10);
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null && person.getEmail().equals(email) && !person.getAccountLockedFlag()){
				person.setOneTimePassword(otp);
				person.setOtpExpiry(cal.getTime());
				session.update(person);
				return true;
			}
			else
				return false;
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
	public boolean updateUserDetails(String userId, String fname, String mname, String lname, String email, String address) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null){
				if(fname!=null) person.setFirstName(fname);
				if(mname!=null) person.setMiddleName(mname);
				if(lname!=null) person.setLastName(lname);
				if(email!=null) person.setEmail(email);
				if(address!=null) person.setAddress(address);
			}
			session.update(person);
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
	public boolean updateUserDetails(String userId, String fname, String mname,	String lname, String email, String address,
			boolean accountLockedFlag) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null){
				if(fname!=null) person.setFirstName(fname);
				if(mname!=null) person.setMiddleName(mname);
				if(lname!=null) person.setLastName(lname);
				if(email!=null) person.setEmail(email);
				if(address!=null) person.setAddress(address);
				person.setAccountLockedFlag(accountLockedFlag);
			}
			session.update(person);
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
	public boolean updatePassword(String userId, String password) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null)
				person.setPassword(password);
			session.update(person);
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
	public boolean updateAccessFlag(String userId, boolean accessFlag) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null)
				person.setAllowAccessFlag(accessFlag);
			session.update(person);
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
	public boolean updateFailedLoginAttempt(String userId) {
		Session session = null;
		try{
			Calendar cal = Calendar.getInstance();
			Date currentDate = cal.getTime();
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person == null)
				return false;
			
			if(person.getLastLoginFailure()==null)
				person.setLoginAttempts(1);
			else{
				Calendar c = Calendar.getInstance();
				c.setTime(person.getLastLoginFailure());
				c.add(Calendar.DATE, 1);
				if(currentDate.compareTo(c.getTime())>=0)
					person.setLoginAttempts(1);
				else if(person.getLoginAttempts()<2)
					person.setLoginAttempts(person.getLoginAttempts()+1);
				else if(person.getLoginAttempts()>=2){
					person.setLoginAttempts(person.getLoginAttempts()+1);
					person.setAccountLockedFlag(true);
				}
			}
			person.setLastLoginFailure(currentDate);
			session.update(person);
			
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
	public boolean updatePIIRequestFlag(String userId, boolean piiRequestFlag) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null)
				person.setPiiRequestFlag(piiRequestFlag);
			session.update(person);
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
	public boolean resetFailedLoginAttempt(String userId) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person == null)
				return false;
			
			person.setLoginAttempts(0);
			person.setLastLoginFailure(null);
			session.save(person);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean authenticateOTP(String userId, String otp) {
		Session session = null;
		try{
			Calendar cal = Calendar.getInstance();
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null && person.getOneTimePassword().equals(otp) && (person.getOtpExpiry().compareTo(cal.getTime())>=0) )
				return true;
			
			return false;
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
	public boolean authenticateUser(String userId, String password) {
		Session session = null;
		try{
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			if(person != null && person.getPassword().equals(password))
				return false;
			else
				return false;
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
	public Person fetchUserById(String userId) {
		Session session = null;
		Person person = null;
		try {
			session = factory.getCurrentSession();
			person = (Person)session.get(Person.class, userId);
			return person;
		}
		catch(Exception e){
			e.printStackTrace();
			return person;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public List<Person> fetchUserByRole(String rolename) {
		Session session = null;
		List<Person> listPerson = new ArrayList<Person>();
		try{
			session = factory.getCurrentSession();
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
		finally{
			//HibernateUtil.shutdown();
		}
	}

	@Override
	public boolean deleteUser(String userId) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			Person person = (Person)session.get(Person.class, userId);
			session.delete(person);
			return true;
		}
		catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		finally{
			//HibernateUtil.shutdown();
		}
	}

	
}
