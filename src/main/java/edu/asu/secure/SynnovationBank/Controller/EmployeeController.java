package edu.asu.secure.SynnovationBank.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.secure.SynnovationBank.FormBean.*;
import edu.asu.secure.SynnovationBank.Service.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeNotificationsService employeeNotificationService;
	@Autowired
	private AdminNotificationsService adminNotificationService;

	@Autowired
	private EmployeeUserAccountService employeeUserAccountService;

	@Autowired
	private EmployeeUserTransactionService employeeUserTransactionService;

	protected static Logger logger = Logger.getLogger("controller");
	
	
	//This is to display all the Employee notifications
    @RequestMapping(value = "/home", method = RequestMethod.GET)
   	 public String getEmployeePage( ModelMap model) {
  
    	model.put("empNotifFormBean", employeeNotificationService.notifications());
    	logger.debug("Received request to show employee page");
    	
    	return "employeepage";
	}

   
  
    @RequestMapping(value = "/employeeviewtransactions", method = {RequestMethod.GET, RequestMethod.POST})
    public String getUserTransactionsPage(@RequestParam(value="error", required=false) boolean error,ModelMap model) {
    	
    	if(error==true){
    		model.put("error","Sorry! You don't have an access. A request has been sent to the user");
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
			System.out.println("You are now sending a notification to the user");
			System.out.println("The usernaem"+usertransactionFormBean.getUserName());
			employeeUserTransactionService.sendNotification(usertransactionFormBean.getUserName());
			model.put("error", true);
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
       
   
    @RequestMapping(value = "/employeeviewmerchanttransactions", method = RequestMethod.GET)
    public String getEmployeeChangePasswordPage( ModelMap model) {
		 
    	
        	logger.debug("Received request to show admin critical transactions page");
        
            model.put("adminCriticalNotifFormBean", employeeNotificationService.merchantRequestsCriticalTrans());
    	return "EmployeeChangePassword";
	}
    

    @RequestMapping(value = "/employeetransactiondeclined/{userName}/{transactionId}/{notificationId}", method = RequestMethod.POST)
    public String adminTransactionDeclined(@PathVariable("userName") String userId, @PathVariable("transactionId") Long transactionId, @PathVariable("notificationId") Long notificationId, ModelMap model, HttpServletRequest request) {
    	logger.debug("Received request to delete user with Id: " + userId);
    	
    	employeeNotificationService.sendTransactionDeclinedNotification(userId, transactionId,notificationId);
        	return "redirect:/secure/employee/employeeviewmerchanttransactions";
  	
	}
    
    @RequestMapping(value = "/employeetransactionaccepted/{userName}/{transactionId}/{notificationId}", method ={RequestMethod.GET, RequestMethod.POST})
    public String adminTransactionAccepted(@PathVariable("userName") String userId, @PathVariable("transactionId") Long transactionId, @PathVariable("notificationId") Long notificationId, ModelMap model, HttpServletRequest request)
    {
    	
    	logger.debug("Received request to accept critical transaction");
    	
    	employeeNotificationService.sendTransactionAcceptedNotification(userId, transactionId, notificationId);
        	return "redirect:/secure/employee/employeeviewmerchanttransactions";
    }
    
    @RequestMapping(value = "/enablejavascript", method =  RequestMethod.GET)
    public String getEnableJavascriptPage(ModelMap model, HttpServletRequest request){
    		
//        public String adminTransactionAccepted(@RequestParam(value="notification", required=true) AdminCriticalTransactionsFormBean notification, HttpServletRequest request,  
//                HttpServletResponse response, ModelMap model) {
    	
    	
        	return "EnableJavascript";
    }


}