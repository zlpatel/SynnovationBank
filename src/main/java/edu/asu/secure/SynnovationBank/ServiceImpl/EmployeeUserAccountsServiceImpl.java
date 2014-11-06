	package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Account;

import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;

import edu.asu.secure.SynnovationBank.Dao.PersonDAO;

import edu.asu.secure.SynnovationBank.FormBean.EmpUserAccFormBean;
import edu.asu.secure.SynnovationBank.Service.EmployeeUserAccountService;

@Service
@Transactional
public class EmployeeUserAccountsServiceImpl implements EmployeeUserAccountService{
	  
    @Autowired
    private PersonDAO personDAO;
     
    
 
    @Transactional
    public List<EmpUserAccFormBean> userAccounts() {
    	 
    	EmpUserAccFormBean empUserAccFormBean=null;
    	
    	List<Person> person=personDAO.fetchUserByRole("ROLE_CUST");
    	List<EmpUserAccFormBean> list=new ArrayList<EmpUserAccFormBean>();
    	
    	for(Person per: person){
    		empUserAccFormBean = new EmpUserAccFormBean();
    		
    		empUserAccFormBean.setFirstName(per.getFirstName());
    		empUserAccFormBean.setLastName(per.getLastName());
    		empUserAccFormBean.setUserName(per.getUserId());
    		empUserAccFormBean.setGetAccountNumber(per.getAccount().getAccountNumber());
    		empUserAccFormBean.setGetBalance(per.getAccount().getBalance());
    		list.add(empUserAccFormBean);
    	}
    	return list;
         
    }



}
