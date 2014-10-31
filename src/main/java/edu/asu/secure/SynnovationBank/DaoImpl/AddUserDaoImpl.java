package edu.asu.secure.SynnovationBank.DaoImpl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.asu.secure.SynnovationBank.Dao.AddUserDao;;

@Repository
public class AddUserDaoImpl implements AddUserDao{

	@Override
	public boolean addUser(String fname) {
		// TODO Auto-generated method stub
		return true;
	}

}
