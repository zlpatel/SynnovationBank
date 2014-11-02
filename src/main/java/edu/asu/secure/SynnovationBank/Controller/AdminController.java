package edu.asu.secure.SynnovationBank.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.secure.SynnovationBank.FormBean.ExternalUserFormBean;
import edu.asu.secure.SynnovationBank.FormBean.InternalUserFormBean;
import edu.asu.secure.SynnovationBank.Service.AddExternalUserService;
import edu.asu.secure.SynnovationBank.Service.AddInternalUserService;

/**
 * Handles and retrieves the common or admin page depending on the URI template.
 * A user must be log-in first he can access these pages.  Only the admin can see
 * the adminpage, however.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AddExternalUserService addExternalUserService;
	@Autowired
	private AddInternalUserService addInternalUserService;

	protected static Logger logger = Logger.getLogger("controller");
	
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
    

    @RequestMapping(value = "/adminaddedexternaluseraccounts")
    public String getAdminAddedExternalUserAccounts(@ModelAttribute("addexternaluserformbean")
    ExternalUserFormBean addexternaluserformbean, BindingResult result,ModelMap model, HttpSession session, HttpServletRequest request) {
    	logger.debug("Received request to show ADDED external user page ......");
       	
    	System.out.println(addexternaluserformbean.getFname());
    	
    	if(addExternalUserService.addExternalUser(addexternaluserformbean))
    	{
    		model.put("message", "User Added Successfuly");
			logger.debug("User Added Successfuly");
	    	return "AdminExternalUserAccounts";
		}
    	
    	else
    	{
			model.put("error","true");
			logger.debug("Some error adding new user!");
			return "redirect:adminaddedexternaluseraccounts";
		}
    	
	}
    
    @RequestMapping(value = "/adminaddedinternaluseraccounts")
    public String getAdminAddedInternalUserAccounts(@ModelAttribute("addinternaluserformbean")
    InternalUserFormBean addinternaluserformbean, BindingResult result,ModelMap model, HttpSession session, HttpServletRequest request) {
    	logger.debug("Received request to show ADDED internal user page ......");
       	
    	System.out.println(addinternaluserformbean.getFname());
    	
    	if(addInternalUserService.addInternalUser(addinternaluserformbean))
    	{
    		model.put("message", "User Added Successfuly");
			logger.debug("User Added Successfuly");
	    	return "AdminInternalUserAccounts";
		}
    	
    	else
    	{
			model.put("error","true");
			logger.debug("Some error adding new user!");
			return "redirect:adminaddedinternaluseraccounts";
		}
    	
	}
}
