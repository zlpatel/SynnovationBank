package edu.asu.secure.SynnovationBank.Service;

public interface CustomerInfoChangeService {
	
	

	boolean changeCustomerInformation(String userName, String firstName,
			String middleName, String lastName, String address, String email);

}
