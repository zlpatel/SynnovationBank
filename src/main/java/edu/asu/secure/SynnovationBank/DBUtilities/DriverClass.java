package edu.asu.secure.SynnovationBank.DBUtilities;

import edu.asu.secure.SynnovationBank.DaoImpl.PersonDAOImpl;

public class DriverClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HibernateUtil.createTables();
		SeedData.insertRoles();
		//SeedData.insertUser();
		//insertUser();
		
		//PersonDAOImpl personDAO = new PersonDAOImpl();
		//System.out.println("Authentication = "+personDAO.authenticateUser("jeff", "jeff"));
		
		//personDAO.updateOTP("jeff", "987");		
		
	}

}
