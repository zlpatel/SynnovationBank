package edu.asu.secure.SynnovationBank.Controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    @RequestMapping(value = "/viewTransaction", method = {RequestMethod.POST,RequestMethod.GET})
  	 public String getUserTransactions(@RequestParam(value="error", required=false) boolean error,@ModelAttribute("usertransactionFormBean") UserTransactionFormBean usertransactionFormBean, ModelMap model) {
   		 
    	if(error==true){
			model.put("error", "You don't have access to this account");	
		}else{
			model.put("error","");
			if(employeeUserTransactionService.checkFlag(usertransactionFormBean.getAccountNumber()))
			{
				logger.debug("Received request to show otp page");
				model.put("userTransaction", employeeUserTransactionService.getTransactions(usertransactionFormBean.getAccountNumber()));
				
			}else{
				model.put("error", true);
				return "redirect:EmployeeViewTransactions";
			}
		}
		return "ViewUserTransactions";
	}
    
   /* @RequestMapping(value = "/notificationAccepted", method = {RequestMethod.POST,RequestMethod.GET})
 	 public String getUserTransactions(@RequestParam(value="error", required=false) boolean error,@ModelAttribute("usertransactionFormBean") UserTransactionFormBean usertransactionFormBean, ModelMap model) {
  		 
   	if(error==true){
			model.put("error", "You don't have access to this account");	
		}else{
			model.put("error","");
			if(employeeUserTransactionService.checkFlag(usertransactionFormBean.getAccountNumber()))
			{
				logger.debug("Received request to show otp page");
				model.put("userTransaction", employeeUserTransactionService.getTransactions(usertransactionFormBean.getAccountNumber()));
				
			}else{
				model.put("error", true);
				return "redirect:EmployeeViewTransactions";
			}
		}
		return "ViewUserTransactions";
	}*/

    
    @RequestMapping(value = "/employeeuseraccounts", method = RequestMethod.GET)
    public String getEmployeeUserAccountsPage(ModelMap model) {
    	
    	model.put("empUserAccFormBean", employeeUserAccountService.userAccounts());
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
