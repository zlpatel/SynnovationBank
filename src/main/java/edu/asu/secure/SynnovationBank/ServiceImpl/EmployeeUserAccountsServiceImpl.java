package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.Dao.AccountDAO;
import edu.asu.secure.SynnovationBank.Dao.NotificationsDAO;
import edu.asu.secure.SynnovationBank.FormBean.EmpNotifFormBean;
import edu.asu.secure.SynnovationBank.FormBean.EmpUserAccFormBean;
import edu.asu.secure.SynnovationBank.Service.EmployeeUserAccountService;

@Service
@Transactional
public class EmployeeUserAccountsServiceImpl implements EmployeeUserAccountService{
	  
    @Autowired
    private AccountDAO accountDAO;
     
    
 
    @Transactional
    public List<EmpUserAccFormBean> userAccounts() {
    	 
    	EmpUserAccFormBean empUserAccFormBean=null;
    	List<Account> account=accountDAO.fetchUserAccounts();
    	List<EmpUserAccFormBean> list=new ArrayList<EmpUserAccFormBean>();
    	
    	for(Account acc: account){
    		empUserAccFormBean = new EmpUserAccFormBean();
    		
    		empUserAccFormBean.setFirstName(acc.getPerson().getFirstName());
    		empUserAccFormBean.setLastName(acc.getPerson().getLastName());
    		empUserAccFormBean.setGetAccountNumber(acc.getAccountNumber());
    		empUserAccFormBean.setGetBalance(acc.getBalance());
    		list.add(empUserAccFormBean);
    	}
    	return list;
         
    }



}
