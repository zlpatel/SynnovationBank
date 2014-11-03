package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.FormBean.EmpUserAccFormBean;
import edu.asu.secure.SynnovationBank.FormBean.ExternalUserFormBean;
import edu.asu.secure.SynnovationBank.FormBean.InternalUserFormBean;
import edu.asu.secure.SynnovationBank.Service.AdminUserAccountsService;

@Service
@Transactional
public class AdminUserAccountsServiceImpl implements AdminUserAccountsService {

	@Autowired
	PersonDAO personDAO;

	@Override
	public List<InternalUserFormBean> getInternalUserAccounts() {
		// TODO Auto-generated method stub
		InternalUserFormBean internalUserFormBean=null;
		//change account to person and pass argument "c"
		List<Person> person=personDAO.fetchUserByRole("ROLE_BNK_EMPL");
		List<InternalUserFormBean> list=new ArrayList<InternalUserFormBean>();

		for(Person per: person){
			internalUserFormBean = new InternalUserFormBean();

			internalUserFormBean.setFname(per.getFirstName());
			internalUserFormBean.setLname(per.getLastName());
			internalUserFormBean.setUsername(per.getUserId());
			list.add(internalUserFormBean);
		}
		return list;

	}

	@Override
	public List<ExternalUserFormBean> getExternalUserAccounts() {

		ExternalUserFormBean externalUserFormBean=null;
		List<Person> person=personDAO.fetchUserByRole("ROLE_CUST");
		List<ExternalUserFormBean> list=new ArrayList<ExternalUserFormBean>();

		for(Person per: person){
			externalUserFormBean = new ExternalUserFormBean();

			externalUserFormBean.setFname(per.getFirstName());
			externalUserFormBean.setLname(per.getLastName());
			externalUserFormBean.setUsername(per.getUserId());
			externalUserFormBean.setAccountNumber(per.getAccount().getAccountNumber());
			externalUserFormBean.setAccountBalance(per.getAccount().getBalance());
			list.add(externalUserFormBean);
		}
		return list;	

	}

	@Override
	public ExternalUserFormBean fetchPersonById(String userId) {

		ExternalUserFormBean externalUserFormBean=new ExternalUserFormBean();;
		Person person = personDAO.fetchUserById(userId);

		if(person!=null)
		{
			externalUserFormBean.setFname(person.getFirstName());
			externalUserFormBean.setLname(person.getLastName());
			externalUserFormBean.setAddress(person.getAddress());
			externalUserFormBean.setEmail(person.getEmail());
			externalUserFormBean.setUsername(person.getUserId());

			return externalUserFormBean;
		}

		return null;
	}

	@Override
	public boolean updateExternalUserDetails(ExternalUserFormBean externalUserFormBean) 
	{
		String userId = externalUserFormBean.getUsername();
		String fname = externalUserFormBean.getFname();
		String mname = "";
		String lname = externalUserFormBean.getLname();
		String email = externalUserFormBean.getEmail();
		String address = externalUserFormBean.getAddress();
		
		return personDAO.updateUserDetails(userId, fname, mname, lname, email, address);
	}

	@Override
	public boolean updateInternalUserDetails(InternalUserFormBean internalUserFormBean) 
	{
		String userId = internalUserFormBean.getUsername();
		String fname = internalUserFormBean.getFname();
		String mname = "";
		String lname = internalUserFormBean.getLname();
		String email = internalUserFormBean.getEmail();
		String address = internalUserFormBean.getAddress();
		
		return personDAO.updateUserDetails(userId, fname, mname, lname, email, address);
	}

}
