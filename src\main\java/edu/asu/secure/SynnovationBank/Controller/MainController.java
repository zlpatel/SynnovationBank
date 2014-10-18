package edu.asu.secure.SynnovationBank.Controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles and retrieves the common or admin page depending on the URI template.
 * A user must be log-in first he can access these pages.  Only the admin can see
 * the adminpage, however.
 */
@Controller
@RequestMapping("/main")
public class MainController {

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
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage() {
    	logger.debug("Received request to show admin page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "adminpage";
	}
    
    /**
     * Handles and retrieves the employee JSP page that only employees can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getEmployeePage() {
    	logger.debug("Received request to show employee page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "employeepage";
	}
    
    /**
     * Handles and retrieves the customer JSP page that only customers can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String getCustomerPage() {
    	logger.debug("Received request to show customer page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "customerpage";
	}
    
    /**
     * Handles and retrieves the merchant JSP page that only merchants can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/merchant", method = RequestMethod.GET)
    public String getMerchantPage() {
    	logger.debug("Received request to show merchant page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "merchantpage";
	}
    
    //Redirected to sessionexpired page when user tries to access the page of an expired session
    @RequestMapping(value = "/expiredsession", method = RequestMethod.GET)
    public String getSessionExpiredPage() {
    	logger.debug("Session has been expired page");
    
    	return "sessionexpired";
	}
    
    @RequestMapping(value = "/sessioninvalid", method = RequestMethod.GET)
    public String getInvalidSessionPage() {
    	logger.debug("Session is invalid");
       	
    	return "invalidsession";
	}
    
    //Redirected to maxsessionreached page when a user tries to log in to the application from two different browsers or systems simultaneously 
    @RequestMapping(value = "/maxsession", method = RequestMethod.GET)
    public String getMaxSessionReachedPage() {
    	logger.debug("Max no. of sesssion reached");
       	
    	return "maxsessionreached";
	}
    
}
