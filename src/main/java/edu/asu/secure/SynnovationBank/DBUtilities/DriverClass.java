package edu.asu.secure.SynnovationBank.DBUtilities;

import edu.asu.secure.SynnovationBank.DaoImpl.PersonDAOImpl;

public class DriverClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HibernateUtil.createTables();
		long admin,empl,cust,merc;
		admin=SeedData.insertRoles("ROLE_ADMIN");
		empl=SeedData.insertRoles("ROLE_BNK_EMPL");
		cust=SeedData.insertRoles("ROLE_CUST");
		merc=SeedData.insertRoles("ROLE_MERC");
		SeedData.insertUser();
		//insertUser();
		
		PersonDAOImpl personDAO = new PersonDAOImpl();
		System.out.println("Authentication = "+personDAO.authenticateUser("admin", "admin"));
		
		personDAO.updateOTP("admin", "987");		
		
	}

}
