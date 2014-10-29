package edu.asu.secure.SynnovationBank.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	protected static Logger logger = Logger.getLogger("controller");
	/**
     * Handles and retrieves the employee JSP page that only employees can see
     * 
     * @return the name of the JSP page
     */
	/**
     * Handles and retrieves the customer JSP page that only customers can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getCustomerPage() {
    	logger.debug("Received request to show customer page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "customerpage";
	}
    
}