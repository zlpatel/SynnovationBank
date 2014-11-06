/**
 * 
 */
package edu.asu.secure.SynnovationBank.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.secure.SynnovationBank.Service.SecurityContextAccessor;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController {
        
	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	SecurityContextAccessor securityContextAccessor;
	
	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error, 
			ModelMap model,HttpServletRequest request,HttpSession session) {
		logger.debug("Received request to show login page");

		// Add an error message to the model if login is unsuccessful
		// The 'error' parameter is set to true based on the when the authentication has failed. 
		// We declared this under the authentication-failure-url attribute inside the spring-security.xml
		
		if (securityContextAccessor.isCurrentAuthenticationAnonymous()) {
			if (error == true) {
				// Assign an error message
				model.put("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
//				model.put("error", "You have entered an invalid username or password!");
			} else {
				model.put("error", "");
			}
		    return "loginpage";
		 } else { 
			 System.out.println(session.getAttribute("USERNAME"));
		    return securityContextAccessor.determineDefaultTargetUrl();
		 }
		
	}
	
	private String getErrorMessage(HttpServletRequest request, String key){
		 
		Exception exception = 
                   (Exception) request.getSession().getAttribute(key);
 
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		}else if(exception instanceof LockedException) {
			error = exception.getMessage();
		}else{
			error = "Invalid username and password!";
		}
 
		return error;
	}
	
	/**
	 * Handles and retrieves the denied JSP page. This is shown whenever a regular user
	 * tries to access an admin only page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
 	public String getDeniedPage() {
		logger.debug("Received request to show denied page");
		
		// This will resolve to /WEB-INF/jsp/deniedpage.jsp
		return "deniedpage";
	}
	
}