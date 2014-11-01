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
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.secure.SynnovationBank.FormBean.CreditFormBean;
import edu.asu.secure.SynnovationBank.FormBean.DebitFormBean;
import edu.asu.secure.SynnovationBank.FormBean.TransferFormBean;
import edu.asu.secure.SynnovationBank.Service.CreditService;
import edu.asu.secure.SynnovationBank.Service.DebitService;
import edu.asu.secure.SynnovationBank.Service.TransferService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	protected static Logger logger = Logger.getLogger("controller");
	@Autowired
	private CreditService creditService;
	@Autowired
	private DebitService debitService;
	@Autowired
	private TransferService transferService;
	
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
    
    
	
	
	// controller for crediting
	
	@RequestMapping(value = "/creditrequest", method = RequestMethod.GET)
    public String getDebitPage(@ModelAttribute("creditFormBean") CreditFormBean creditFormBean, HttpServletRequest request, HttpSession session) {

		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		logger.debug("Received request to show creditrequest page");
		System.out.println("credited amount :"+creditFormBean.getCreditAmount()+"to Account:" +userName );
			if(creditService.creditAmount(userName,creditFormBean.getCreditAmount()))
				return "welcomeUser";
			
			else
				return "credit_debit";
    	
	}
	

	
	
	// controller for debiting
	
		@RequestMapping(value = "/debitrequest", method = RequestMethod.GET)
	    public String getDebitPage(@ModelAttribute("debitFormBean") DebitFormBean debitFormBean, HttpServletRequest request, HttpSession session) {

			String userName="";
			session = request.getSession(false);
	        if (session != null) {
	            userName=(String)request.getSession().getAttribute("USERNAME");
	        }
			
			logger.debug("Received request to show debitrequest page");
			System.out.println("Debited amount :"+debitFormBean.getDebitAmount()+"to Account:" +userName );
				if(debitService.debitAmount(userName, debitFormBean.getDebitAmount()))
					return "welcomeUser";
				
				else
					return "debit";
	    	
		}

	
	
	
	
	

	
		// controller for 		transferrequest
		
		@RequestMapping(value = "/transferrequest", method = RequestMethod.GET)
	    public String getDebitPage(@ModelAttribute("transferFormBean") TransferFormBean transferFormBean, HttpServletRequest request, HttpSession session) {
			
			String userName="";
			session = request.getSession(false);
	        if (session != null) {
	            userName=(String)request.getSession().getAttribute("USERNAME");
	        }

			logger.debug("Received request to show transfer rqst page");
			System.out.println("Send from:" +userName); 
			System.out.println("Send to :"+transferFormBean.getReceiverID());
			System.out.println("Transfer amount :"+transferFormBean.getTransferAmount());
				
			if(transferService.performTransfer(userName, transferFormBean.getReceiverID(),transferFormBean.getTransferAmount()))
				return "welcomeUser";
			else
					return "transfer";
	    	
		}
	
	
	 @RequestMapping(value = "/debit", method = RequestMethod.GET)
	    public String getDebit() {
	    	logger.debug("Received request to show credit/debit page");
	    
	    	// Do your work here. Whatever you like
	    	// i.e call a custom service to do your business
	    	// Prepare a model to be used by the JSP page
	    	
	    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
	    	return "debit";
		}
		
	
	
	
	
	
	
    
    
    @RequestMapping(value = "/credit_debit", method = RequestMethod.GET)
    public String getCreditDebit() {
    	logger.debug("Received request to show credit/debit page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "credit_debit";
	}
	
	
	
	@RequestMapping(value = "/changeCustomerInfo", method = RequestMethod.GET)
    public String getChaneCustomerInfo() {
    	logger.debug("Received request to show Change customer information page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "changeCustomerInfo";
	}
	
	
	
	@RequestMapping(value = "/customerNotifications", method = RequestMethod.GET)
    public String getCustomerNotifications() {
    	logger.debug("Received request to show customer notifications page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "customerNotifications";
	}
	
	
	
	
	
	@RequestMapping(value = "/techAccountAccess", method = RequestMethod.GET)
    public String gettechAccountAccess() {
    	logger.debug("Received request to show techAccountAccess page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "techAccountAccess";
	}
	
	
	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String gettransfer() {
    	logger.debug("Received request to show transfer page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "transfer";
	}
	
	
	
	@RequestMapping(value = "/viewTransactions", method = RequestMethod.GET)
    public String getviewTransactions() {
    	logger.debug("Received request to show viewTransactions page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "viewTransactions";
	}
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getwelcomeUser() {
    	logger.debug("Received request to show welcomeUser page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "welcomeUser";
	}
	
	
    
    
    
    
    
    
    
    
    
    
}