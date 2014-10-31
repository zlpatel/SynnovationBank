package edu.asu.secure.SynnovationBank.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.secure.SynnovationBank.FormBean.ChangePasswordFormBean;

/**
 * Handles and retrieves the common or admin page depending on the URI template.
 * A user must be log-in first he can access these pages.  Only the admin can see
 * the adminpage, however.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	protected static Logger logger = Logger.getLogger("controller");
	
	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/common", method = RequestMethod.GET)
    public String getCommonPage() {
    	logger.debug("Received request to show common page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "commonpage";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getAdminPage(HttpServletRequest request) {
    	logger.debug("Received request to show admin page");
    
    	 HttpSession ses=request.getSession();
		 ses.setAttribute("sessionVar","admin");
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "adminpage";
	}
    
    
    
    
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/admininternaluseraccounts", method = RequestMethod.GET)
    public String getAdminInternalUserAccountsPage() {
    	logger.debug("Received request to show admin internal user accounts page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/AdminInternalUserAccounts.jsp
    	return "AdminInternalUserAccounts";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/adminexternaluseraccounts", method = RequestMethod.GET)
    public String getAdminExternalUserAccountsPage() {
    	logger.debug("Received request to show admin external user accounts page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/AdminExternalUserAccounts.jsp
    	return "AdminExternalUserAccounts";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/adminpiirequests", method = RequestMethod.GET)
    public String getAdminPIIRequestsPage() {
    	logger.debug("Received request to show admin pii requests page");
    	
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/AdminPIIRequests.jsp
    	return "AdminPIIRequests";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/adminsystemlog", method = RequestMethod.GET)
    public String getAdminAdminSystemLogPage() {
    	logger.debug("Received request to show admin system log page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/AdminSystemLog.jsp
    	return "AdminSystemLog";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/admincriticaltransactions", method = RequestMethod.GET)
    public String getAdminAdminCriticalTransactionsPage() {
    	logger.debug("Received request to show admin critical transactions page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/AdminCriticalTransactions.jsp
    	return "AdminCriticalTransactions";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/adminchangepassword", method = RequestMethod.GET)
    public String getAdminChangePasswordPage() {
    	logger.debug("Received request to show admin change password page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/AdminChangePassword.jsp
    	return "AdminChangePassword";
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
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/adminaddexternaluser", method = RequestMethod.GET)
    public String getAdminAddExternalUser() {
    	logger.debug("Received request to show add external user page");
    
    	return "AdminAddExternalUser";
    	
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/adminaddinternaluser", method = RequestMethod.GET)
    public String getAdminAddInternalUser() {
    	logger.debug("Received request to show add internal user page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/AdminAddInternalUser.jsp
    	return "AdminAddInternalUser";
	}
    
    @RequestMapping(value = "/adminaddedexternaluseraccounts", method = RequestMethod.POST)
    public String getAdminAddedExternalUser(@ModelAttribute("addexternaluserformbean")
    ChangePasswordFormBean changePasswordFormBean, BindingResult result,ModelMap model) {
    	logger.debug("Received request to show ADDED external user page ......");
    
//    	String name = (String) request.getAttribute("fname");
//    	logger.debug("got name " + name);
//    	
//    	// Do your work here. Whatever you like
//    	// i.e call a custom service to do your business
//    	// Prepare a model to be used by the JSP page
//    	
//    	ModelAndView model = new ModelAndView("AdminExternalUserAccounts");
//    	model.addObject("rohit", name);
//    	// This will resolve to /WEB-INF/jsp/AdminAddExternalUser.jsp
////    	return "AdminAddExternalUser";
//    	
//    	return model;
    	
    	return "";
	}
}
