package edu.asu.secure.SynnovationBank.Controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.FormBean.*;
import edu.asu.secure.SynnovationBank.Service.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeNotificationsService employeeNotificationService;

	@Autowired
	private EmployeeUserAccountService employeeUserAccountService;

	@Autowired
	private EmployeeUserTransactionService employeeUserTransactionService;

	protected static Logger logger = Logger.getLogger("controller");
	/**
     * Handles and retrieves the employee JSP page that only employees can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
   	 public String getEmployeePage( ModelMap model) {
    		 
    	        //model.put("empNotification", new Notifications());
    	        model.put("empNotifFormBean", employeeNotificationService.notifications());
    	        logger.debug("Received request to show employee page");
    	       
    	    
    	//return "empNotifFormBean";----should this be written?
    	
    	return "employeepage";
	}

   
  
    @RequestMapping(value = "/employeeviewtransactions", method = {RequestMethod.GET, RequestMethod.POST})
    public String getUserTransactionsPage(@RequestParam(value="error", required=false) boolean error,ModelMap model) {
    	
    	if(error==true){
    		model.put("error","you don't have an access/ Account number doesn't exist!");
    	}
    	else{
    		model.put("error","");
    	}
    	logger.debug("Received request to show employee view transactions page");
    	
    		return "EmployeeViewTransactions";
	}
    
    @RequestMapping(value = "/viewtransactions", method = RequestMethod.POST)
    public String getUserTransactions(@ModelAttribute("usertransactionformbean") UserTransactionFormBean usertransactionFormBean,BindingResult result, ModelMap model) {
    	boolean f;
    	System.out.println("The user name : "+usertransactionFormBean.getUserName());
    	f=employeeUserTransactionService.checkFlag(usertransactionFormBean.getUserName());
    	System.out.println("The flag is " + f);
    	if(f)
		{
			logger.debug("Received access to show transactions page");
			List<UserTransactionFormBean> l=employeeUserTransactionService.getTransactions(usertransactionFormBean.getUserName());
			System.out.println("size of the list is"+ l.size());
			model.put("userTransaction", employeeUserTransactionService.getTransactions(usertransactionFormBean.getUserName()));
			
		}else{
			
			model.put("error", "Sorry! You don't have access to this account. A request has been sent to the user");
			employeeUserTransactionService.sendNotification();
			return "redirect:employeeviewtransactions";
		}
    
    	
    	return "ViewUserTransactions";
	}
    
    @RequestMapping(value = "/employeeuseraccounts", method = RequestMethod.GET)
    public String getEmployeeUserAccountsPage(ModelMap model) {
    	List<EmpUserAccFormBean> list=employeeUserAccountService.userAccounts();
    	logger.debug("display "+list.size());
    	
    	model.put("empUserAcc", list);
    	logger.debug("Received request to show employee user accounts page");
    
    	
    	return "EmployeeUserAccounts";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/employeechangepassword", method = RequestMethod.GET)
    public String getEmployeeChangePasswordPage() {
    	logger.debug("Received request to show employee change password page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/AdminChangePassword.jsp
    	return "EmployeeChangePassword";
	}
}
