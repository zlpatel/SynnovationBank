package edu.asu.secure.SynnovationBank.DaoImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.secure.SynnovationBank.DTO.Role;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.Dao.RoleDAO;

@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	SessionFactory factory;

	@Override
	public long insertRole(Role role) {
		long roleId = -1;
		try{
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			roleId = (Long)session.save(role);
			Set<TransactionType> set = role.getTransactionType();
			Iterator<TransactionType> itr = set.iterator();
			while(itr.hasNext())
				session.save(itr.next());
			session.getTransaction().commit();
			return roleId;
		}
		catch(Exception e){
			e.printStackTrace();
			return roleId;
		}
	}

	@Override
	public Role fetchRole(String roleName) {
		Role role = null;
		List list = null;
		try{
			Session session = factory.getCurrentSession();
			Query query = session.createQuery("from Role where roleName= :name");
			query.setString("name", roleName);
			list = query.list();
			session.getTransaction().commit();
			if(list!=null)
				role = (Role)list.get(0);
		}
		catch(Exception e){
			e.printStackTrace();
			return role;
		}
		return role;
	}
	
	
}
