package edu.asu.secure.SynnovationBank.DaoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DBUtilities.HibernateUtil;
import edu.asu.secure.SynnovationBank.DTO.Role;
import edu.asu.secure.SynnovationBank.Dao.RoleDAO;

@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	SessionFactory factory = HibernateUtil.buildSessionFactory();

	@Override
	public long insertRole(Role role) {
		long roleId = -1;
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			roleId = (Long)session.save(role);
			session.getTransaction().commit();
			return roleId;
		}
		catch(Exception e){
			e.printStackTrace();
			return roleId;
		}
	}
	
	
}
