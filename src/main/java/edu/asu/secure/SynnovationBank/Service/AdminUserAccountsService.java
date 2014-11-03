package edu.asu.secure.SynnovationBank.Service;

import java.util.List;

import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.FormBean.ExternalUserFormBean;
import edu.asu.secure.SynnovationBank.FormBean.InternalUserFormBean;

public interface AdminUserAccountsService {

	List<InternalUserFormBean> getInternalUserAccounts();
	List<ExternalUserFormBean> getExternalUserAccounts();
	ExternalUserFormBean fetchPersonById(String userId);
	boolean updateExternalUserDetails(ExternalUserFormBean externalUserFormBean);
	boolean updateInternalUserDetails(InternalUserFormBean internalUserFormBean);	
	boolean deleteUserDetails(String userId);

}