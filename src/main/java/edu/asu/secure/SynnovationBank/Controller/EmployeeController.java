package edu.asu.secure.SynnovationBank.Controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	protected static Logger logger = Logger.getLogger("controller");
	/**
     * Handles and retrieves the employee JSP page that only employees can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getEmployeePage() {
    	logger.debug("Received request to show employee page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "employeepage";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/employeeuseraccounts", method = RequestMethod.GET)
    public String getEmployeeUserAccountsPage() {
    	logger.debug("Received request to show employee user accounts page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/EmployeeUserAccounts.jsp
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
