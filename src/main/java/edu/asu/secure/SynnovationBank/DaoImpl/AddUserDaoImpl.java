package edu.asu.secure.SynnovationBank.DaoImpl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.asu.secure.SynnovationBank.Dao.AddUserDao;
import edu.asu.secure.SynnovationBank.FormBean.ExternalUserFormBean;


@Repository
public class AddUserDaoImpl implements AddUserDao{

	@Override
	public boolean addUser(ExternalUserFormBean addexternaluserformbean) {
		// TODO Auto-generated method stub
		return true;
	}

}
