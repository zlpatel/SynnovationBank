package edu.asu.secure.SynnovationBank.Controller;

import java.util.ArrayList;
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

import edu.asu.secure.SynnovationBank.FormBean.AdminCriticalTransactionsFormBean;
import edu.asu.secure.SynnovationBank.FormBean.ExternalUserFormBean;
import edu.asu.secure.SynnovationBank.FormBean.InternalUserFormBean;
import edu.asu.secure.SynnovationBank.Service.AddExternalUserService;
import edu.asu.secure.SynnovationBank.Service.AddInternalUserService;
import edu.asu.secure.SynnovationBank.Service.AdminNotificationsService;
import edu.asu.secure.SynnovationBank.Service.AdminUserAccountsService;

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
	@Autowired
	private AdminUserAccountsService adminUserAccountsService;
	@Autowired
	private AdminNotificationsService adminNotificationService;
	

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
    	return "redirect:admininternaluseraccounts";
	}
    
    
    
    
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/admininternaluseraccounts", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAdminInternalUserAccountsPage(ModelMap model) {
    	logger.debug("Received request to show admin internal user accounts page");
    	
    	model.put("internaluserslist",adminUserAccountsService.getInternalUserAccounts());
    	// This will resolve to /WEB-INF/jsp/AdminInternalUserAccounts.jsp
    	return "AdminInternalUserAccounts";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/adminexternaluseraccounts", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAdminExternalUserAccountsPage(ModelMap model) {
    	logger.debug("Received request to show admin external user accounts page");
    
    	model.put("externaluserslist",adminUserAccountsService.getExternalUserAccounts());

    	// This will resolve to /WEB-INF/jsp/AdminExternalUserAccounts.jsp
    	return "AdminExternalUserAccounts";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/adminpiirequests", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAdminPIIRequestsPage(@ModelAttribute("modifyexternaluserformbean")
    ExternalUserFormBean modifyexternaluserformbean, BindingResult result,ModelMap model, HttpSession session, HttpServletRequest request) {
    	logger.debug("Received request to show admin pii requests page");
    	
    	model.put("piirequestslist", adminNotificationService.getPIIRequestNotifications());
    	
    	// This will resolve to /WEB-INF/jsp/AdminPIIRequests.jsp
    	return "AdminPIIRequests";
	}
    
    @RequestMapping(value = "/addadminpiirequest", method = {RequestMethod.GET, RequestMethod.POST})
    public String addAdminPIIRequest(@ModelAttribute("modifyexternaluserformbean")
    ExternalUserFormBean modifyexternaluserformbean, BindingResult result,ModelMap model, HttpSession session, HttpServletRequest request) {
    	logger.debug("Received request to show admin pii requests page");
    	
    	adminNotificationService.addPIIRequestNotification(modifyexternaluserformbean);
    	
    	// This will resolve to /WEB-INF/jsp/AdminPIIRequests.jsp
    	return "redirect:adminpiirequests";
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
    public String getAdminAdminCriticalTransactionsPage(ModelMap model) {
    	logger.debug("Received request to show admin critical transactions page");
    
        model.put("adminCriticalNotifFormBean", adminNotificationService.getCriticalTransactionNotifications());
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
    public String getAdminAddExternalUser(ModelMap model) {
    	logger.debug("Received request to show add external user page");
    	
//    	List<String> rolesList = new ArrayList<String>();
//    	rolesList.add("ROLE_CUST");
//    	rolesList.add("ROLE_MERC");
//    	model.put("rolesList", rolesList);    	
    	
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
    

    @RequestMapping(value = "/adminaddedexternaluseraccounts", method = {RequestMethod.POST, RequestMethod.GET})
    public String getAdminAddedExternalUserAccounts(@ModelAttribute("addexternaluserformbean")
    ExternalUserFormBean addexternaluserformbean, BindingResult result,ModelMap model, HttpSession session, HttpServletRequest request) {
    	logger.debug("Received request to show ADDED external user page ......");
       	
//    	System.out.println(request.getParameter("radios"));
    	addexternaluserformbean.setRole(request.getParameter("radios"));
    	
    	if(addExternalUserService.addExternalUser(addexternaluserformbean))
    	{
    		model.put("message", "User Added Successfuly");
			logger.debug("User Added Successfuly");
	    	return "redirect: adminexternaluseraccounts";
		}
    	
    	else
    	{
			model.put("error","true");
			logger.debug("Some error adding new user!");
			return "redirect:adminaddedexternaluseraccounts";
		}
    	
	}
    
    @RequestMapping(value = "/adminaddedinternaluseraccounts", method = {RequestMethod.POST, RequestMethod.GET})
    public String getAdminAddedInternalUserAccounts(@ModelAttribute("addinternaluserformbean")
    InternalUserFormBean addinternaluserformbean, BindingResult result,ModelMap model, HttpSession session, HttpServletRequest request) {
    	logger.debug("Received request to show ADDED internal user page ......");
       	
    	System.out.println(addinternaluserformbean.getFname());
    	
    	if(addInternalUserService.addInternalUser(addinternaluserformbean))
    	{
    		model.put("message", "User Added Successfuly");
			logger.debug("User Added Successfuly");
	    	return "redirect:admininternaluseraccounts";
		}
    	
    	else
    	{
			model.put("error","true");
			logger.debug("Some error adding new user!");
			return "redirect:adminaddedinternaluseraccounts";
		}
    	
	}
    
    @RequestMapping(value = "/adminmodifyexternaluser", method = RequestMethod.POST)
    public String getAdminModifyExternalUser(@RequestParam(value="userId", required=true) String userId, HttpServletRequest request,  
            HttpServletResponse response, ModelMap model) {
    	logger.debug("Received request to modify user with Id: " + userId);
    	
    	model.put("modifyexternaluserformbean", adminUserAccountsService.fetchPersonById(userId));
    	return "AdminModifyExternalUser";
    	
	}
    
    @RequestMapping(value = "/adminmodifyinternaluser", method = RequestMethod.POST)
    public String getAdminModifyInternalUser(@RequestParam(value="userId", required=true) String userId, HttpServletRequest request,  
            HttpServletResponse response, ModelMap model) {
    	logger.debug("Received request to modify user with Id: " + userId);
    	
    	model.put("modifyinternaluserformbean", adminUserAccountsService.fetchPersonById(userId));
    	return "AdminModifyInternalUser";
    	
	}
    
    
    @RequestMapping(value = "/adminmodifiedexternaluseraccounts", method = {RequestMethod.POST, RequestMethod.GET})
    public String modifyExternalUserAccounts(@ModelAttribute("modifyexternaluserformbean")
    ExternalUserFormBean modifyexternaluserformbean, BindingResult result,ModelMap model, HttpSession session, HttpServletRequest request) {
    	logger.debug("Received request to show modified external user page ......");
       	
    	System.out.println(modifyexternaluserformbean.getFname());
    	
    	if(adminUserAccountsService.updateExternalUserDetails(modifyexternaluserformbean))
    	{
    		model.put("message", "User Modified Successfuly");
			logger.debug("User Modified Successfuly");
	    	return "redirect: adminexternaluseraccounts";
		}
    	
    	else
    	{
			model.put("error","true");
			logger.debug("Some error modifying user!");
			return "AdminExternalUserAccounts";
		}
    	
	}
    
    @RequestMapping(value = "/adminmodifiedinternaluseraccounts", method = {RequestMethod.POST, RequestMethod.GET})
    public String modifyInternalUserAccounts(@ModelAttribute("modifyinternaluserformbean")
    InternalUserFormBean modifyinternaluserformbean, BindingResult result,ModelMap model, HttpSession session, HttpServletRequest request) {
    	logger.debug("Received request to show modified internal user page ......");
       	
    	System.out.println(modifyinternaluserformbean.getFname());
    	
    	if(adminUserAccountsService.updateInternalUserDetails(modifyinternaluserformbean))
    	{
    		model.put("message", "User Modified Successfuly");
			logger.debug("User Modified Successfuly");
	    	return "redirect: admininternaluseraccounts";
		}
    	
    	else
    	{
			model.put("error","true");
			logger.debug("Some error modifying user!");
			return "AdminInternalUserAccounts";
		}
    	
	}
    
    @RequestMapping(value = "/admindeleteinternaluser", method = RequestMethod.POST)
    public String adminDeleteInternalUser(@RequestParam(value="userId", required=true) String userId, HttpServletRequest request,  
            HttpServletResponse response, ModelMap model) {
    	logger.debug("Received request to delete user with Id: " + userId);
    	
    	if(adminUserAccountsService.deleteUserDetails(userId))
    	{
        	return "redirect:admininternaluseraccounts";
    	}
    	
    	else
    	{
			model.put("error","true");
			logger.debug("Some error deleting user!");
			return "AdminInternalUserAccounts";
    	}   	
	}    
        
    @RequestMapping(value = "/admindeleteexternaluser", method = RequestMethod.POST)
    public String adminDeleteExternalUser(@RequestParam(value="userId", required=true) String userId, HttpServletRequest request,  
            HttpServletResponse response, ModelMap model) {
    	logger.debug("Received request to delete user with Id: " + userId);
    	
    	if(adminUserAccountsService.deleteUserDetails(userId))
    	{
        	return "redirect:adminexternaluseraccounts";
    	}
    	
    	else
    	{
			model.put("error","true");
			logger.debug("Some error deleting user!");
			return "AdminExternalUserAccounts";
    	}   	
	}   
    
    
    @RequestMapping(value = "/admintransactiondeclined/{userId}/{transactionId}/{notificationId}", method = {RequestMethod.POST, RequestMethod.GET})
    public String adminTransactionDeclined(@PathVariable("userId") String userId, @PathVariable("transactionId") Long transactionId, @PathVariable("notificationId") Long notificationId, ModelMap model, HttpServletRequest request) {
    	logger.debug("Received request to decline transaction for user with Id: " + userId);
    	
    	adminNotificationService.sendTransactionDeclinedNotification(userId,transactionId, notificationId);
        	return "redirect:/secure/admin/admincriticaltransactions";
    	
//    	else
//    	{
//			model.put("error","true");
//			logger.debug("Some error deleting user!");
//			return "AdminExternalUserAccounts";
//    	}   	
	}   
    
    @RequestMapping(value = "/admintransactionaccepted/{userId}/{transactionId}/{notificationId}", method = {RequestMethod.POST, RequestMethod.GET})
    public String adminTransactionAccepted(@PathVariable("userId") String userId, @PathVariable("transactionId") Long transactionId, @PathVariable("notificationId") Long notificationId, ModelMap model, HttpServletRequest request){
    		
//        public String adminTransactionAccepted(@RequestParam(value="notification", required=true) AdminCriticalTransactionsFormBean notification, HttpServletRequest request,  
//                HttpServletResponse response, ModelMap model) {
    	
    	logger.debug("Received request to accept critical transaction");
    	
    	adminNotificationService.sendTransactionAcceptedNotification(userId, transactionId, notificationId);
        	return "redirect:/secure/admin/admincriticaltransactions";
    }
    
}