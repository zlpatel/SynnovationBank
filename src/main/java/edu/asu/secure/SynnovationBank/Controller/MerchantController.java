package edu.asu.secure.SynnovationBank.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.FormBean.CreditFormBean;
import edu.asu.secure.SynnovationBank.FormBean.DebitFormBean;
import edu.asu.secure.SynnovationBank.FormBean.MerchantInfoChangeFormBean;
import edu.asu.secure.SynnovationBank.FormBean.MerchantNotifFormBean;
import edu.asu.secure.SynnovationBank.FormBean.MerchantPaymentFormBean;
import edu.asu.secure.SynnovationBank.FormBean.TransferFormBean;
import edu.asu.secure.SynnovationBank.Service.MerchantCreditService;
import edu.asu.secure.SynnovationBank.Service.MerchantDebitService;
import edu.asu.secure.SynnovationBank.Service.MerchantInfoChangeService;
import edu.asu.secure.SynnovationBank.Service.MerchantNotificationService;
import edu.asu.secure.SynnovationBank.Service.MerchantTechAccountAccessService;
import edu.asu.secure.SynnovationBank.Service.MerchantTransactionService;
import edu.asu.secure.SynnovationBank.Service.MerchantTransferService;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

	protected static Logger logger = Logger.getLogger("controller");
	@Autowired
	private MerchantCreditService creditService;
	@Autowired
	private MerchantDebitService debitService;
	@Autowired
	private MerchantTransferService transferService;
	@Autowired
	private MerchantInfoChangeService merchantInfoChangeService;
	@Autowired
	private MerchantTechAccountAccessService techAccountAccessService;
	@Autowired
	private MerchantTransactionService merchantTransactionService;
	@Autowired
	private MerchantNotificationService merchantNotificationService; 
	
	
	/**
     * Handles and retrieves the merchant JSP page that only merchants can see
     * 
     * @return the name of the JSP page
     */
	/**
     * Handles and retrieves the merchant JSP page that only merchants can see
     * 
     * @return the name of the JSP page
     */
	@RequestMapping(value = "/changemerchantinforequest", method = RequestMethod.GET)
    public String getNewCustomerInfo(@ModelAttribute("merchantInfoChangeFormBean") MerchantInfoChangeFormBean merchantInfoChangeFormBean, HttpServletRequest request, HttpSession session) {

		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		logger.debug("Received request to show merchant info change rqst page");
		
		System.out.println("New information to be changed: "+ merchantInfoChangeFormBean.getFirstName() + " "+merchantInfoChangeFormBean.getLastName()+" "+merchantInfoChangeFormBean.getEmail());
		
		String firstName=merchantInfoChangeFormBean.getFirstName();
		String middleName=merchantInfoChangeFormBean.getMiddleName();
		String lastName=merchantInfoChangeFormBean.getLastName();
		String address=merchantInfoChangeFormBean.getAddress();
		String email=merchantInfoChangeFormBean.getEmail();
		
		if(firstName=="")
			firstName=null;
		if(middleName=="")
			middleName=null;
		if(lastName=="")
			lastName=null;
		if(address=="")
			address=null;
		if(email=="")
			email=null;
		
		
		
		
		
		if(merchantInfoChangeService.changeCustomerInformation(userName, firstName, middleName,lastName,address,email))
				return "WelcomeMerchant";
			
			else
				return "ChangeMerchantInfo";
    	
	}
	
	
	
	//controller for techaccountaccess
	

	@RequestMapping(value = "/merchanttechaccountaccess", method = RequestMethod.GET)
	public String env(HttpServletRequest request, HttpSession session){

		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		logger.debug("Received request to show change tech account access rqst page");
		
		
		String selection=null;		 
             if(request.getParameter("radios").equals("radio1")) {
            	 selection="Y";
            	 System.out.println("**********************************");
                 System.out.println("ALLOW TECHNICAL ACCOUNT ACCESS");
                 System.out.println("**********************************");
             }
             else
             {selection="N";
        	 System.out.println("*****************************************");
             System.out.println("DO NOT ALLOW TECHNICAL ACCOUNT ACCESS");
             System.out.println("*****************************************");
             }
             
             if(techAccountAccessService.setAccessFlag(userName, selection))
            	 	return "WelcomeMerchant";
             else
            	    return "MerchantTechAccountAccess";

    }
	
	//Controller for redirecting when accept notification  by merchant
	@RequestMapping(value = "/merchantacceptexternaluser", method = RequestMethod.POST)
    public String getMerchantAccept(@RequestParam(value="notifId", required=false) long notifId,ModelMap model, HttpServletRequest request, HttpSession session) {

		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		logger.debug("Received request to show merchantsubmitpayment page");
		System.out.println("Accepted Notification :"+notifId);		
		
		Notifications notifObj=merchantNotificationService.getNotificationsById(notifId);
		model.put("merchantPaymentFormBean", merchantNotificationService.getPaymentNotifications(notifObj));
		
		return "MerchantSubmitPayment";
    	
	}
	
	//reject
	@RequestMapping(value = "/merchantrejectexternaluser", method = RequestMethod.POST)
    public String getMerchantReject(@RequestParam(value="notifId", required=false) long notifId,ModelMap model, HttpServletRequest request, HttpSession session) {

		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		logger.debug("Received request to reject merchantsubmitpayment page");
		System.out.println("Rejected Notification :"+notifId);		
		
		return "MerchantSubmitPayment";
    	
	}
	
	//Controller for redirecting when accept notification  by merchant
		@RequestMapping(value = "/submitpayment", method = RequestMethod.POST)
	    public String getSubmitPayment(@RequestParam(value="error", required=false) boolean error,ModelMap model, @ModelAttribute("merchantPaymentFormBean") MerchantPaymentFormBean merchantPaymentFormBean, HttpServletRequest request, HttpSession session) {

			String userName="";
			session = request.getSession(false);
	        if (session != null) {
	            userName=(String)request.getSession().getAttribute("USERNAME");
	        }
			logger.debug("Received request to show submit payment page");	
			
		    //update database
			merchantNotificationService.submitPayment(merchantPaymentFormBean);
			
			return "MerchantSubmitPayment";
	    	
		}
	
	
	@RequestMapping(value = "/MerchantSubmitPayment", method = RequestMethod.POST)
    public String submitPayment(@RequestParam(value="error", required=false) boolean error,ModelMap model) { 
	
		logger.debug("Received request to show credit/debit page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
		
		
		
    	return "MerchantSubmitPayment";
	}
	
	@RequestMapping(value = "/MerchantAcceptNotification", method = RequestMethod.POST)
	public String displayNotifications(ModelMap model, HttpServletRequest request,HttpSession session) {
    	logger.debug("Received request to show merchantAcceptNotification page");
    	
    	String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
        List<MerchantNotifFormBean> mnotif=merchantNotificationService.notifications(userName);
        System.out.println("hello there");
        System.out.println(userName);
        for(MerchantNotifFormBean mnotifbean:mnotif){
        	
        	System.out.println("print ***"+mnotifbean.getTransaction_id());
        	
        }
        
        
        model.put("merchantNotifFormBean", merchantNotificationService.notifications(userName));
		
    	return "MerchantAcceptNotification";
	}
	
	
	
	
	
	
	
	
	
	// controller for crediting
	
	@RequestMapping(value = "/merchantcreditrequest", method = RequestMethod.GET)
    public String getCreditRqstPage(@RequestParam(value="error", required=false) boolean error,ModelMap model, @ModelAttribute("creditFormBean") CreditFormBean creditFormBean, HttpServletRequest request, HttpSession session) {

		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		logger.debug("Received request to show creditrequest page");
		System.out.println("credited amount :"+creditFormBean.getCreditAmount()+"to Account:" +userName );
			if(creditService.creditAmount(userName,creditFormBean.getCreditAmount()))
				return "WelcomeMerchant";
			
			else
			{
				model.put("error","CREDIT UNSUCCESSFULL");
				return "MerchantCredit";
				
			}
    	
	}
	

	
	
	// controller for debiting
	
		@RequestMapping(value = "/merchantdebitrequest", method = RequestMethod.GET)
	    public String getDebitRqstPage(@RequestParam(value="error", required=false) boolean error,ModelMap model,@ModelAttribute("debitFormBean") DebitFormBean debitFormBean, HttpServletRequest request, HttpSession session) {

			String userName="";
			session = request.getSession(false);
	        if (session != null) {
	            userName=(String)request.getSession().getAttribute("USERNAME");
	        }
			
			logger.debug("Received request to show debitrequest page");
			System.out.println("Debited amount :"+debitFormBean.getDebitAmount()+"to Account:" +userName );
				if(debitService.debitAmount(userName, debitFormBean.getDebitAmount()))
					return "WelcomeMerchant";
				
				else
				{
					model.put("error","DEBIT UNSUCCESSFULL");
					return "MerchantDebit";
					
				}
	    	
		}


	
		// controller for 		transferrequest
		
		@RequestMapping(value = "/merchanttransferrequest", method = RequestMethod.GET)
	    public String getTransferRqstPage(@RequestParam(value="error", required=false) boolean error, ModelMap model,@ModelAttribute("transferFormBean") TransferFormBean transferFormBean, HttpServletRequest request, HttpSession session) {
			
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
			{
				
				return "WelcomeMerchant";
			}
			else
			{
				model.put("error","TRANSFER UNSUCCESSFULL");
					return "MechantTransfer";
			}
	    	
		}
	
	
	 @RequestMapping(value = "/MerchantDebit", method = RequestMethod.GET)
	    public String getDebit(@RequestParam(value="error", required=false) boolean error,ModelMap model) {
		 if(error==true){
				model.put("error", "DEBIT NOT SUCCESSFULL !!");	
			}else{
				model.put("error","");
			}
	    
		 
		 logger.debug("Received request to show credit/debit page");
	    
	    	// Do your work here. Whatever you like
	    	// i.e call a custom service to do your business
	    	// Prepare a model to be used by the JSP page
	    	
	    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
	    	return "MerchantDebit";
		}
		
	
	
	
	
	
	
    
    
    @RequestMapping(value = "/MerchantCredit", method = RequestMethod.GET)
    public String getCreditDebit(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
    	if(error==true){
			model.put("error", "CREDIT NOT SUCCESSFULL !!");	
		}else{
			model.put("error","");
		}
    	logger.debug("Received request to show credit/debit page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "MerchantCredit";
	}
	
	
	
	@RequestMapping(value = "/ChangeMerchantInfo", method = RequestMethod.GET)
    public String getChangeMerchantInfo() {
    	logger.debug("Received request to show Change merchant information page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "ChangeMerchantInfo";
	}
	
	
	
	@RequestMapping(value = "/MerchantNotifications", method = RequestMethod.GET)
    public String getMerchantNotifications(ModelMap model, HttpServletRequest request, HttpSession session) {
		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		
		model.put("merchantNotifFormBean", merchantNotificationService.notifications(userName));
        
		
		
		
    	logger.debug("Received request to show customer notifications page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "MerchantNotifications";
	}
	
	
	
	
	
	@RequestMapping(value = "/MerchantTechAccountAccess", method = RequestMethod.GET)
    public String gettechAccountAccess(HttpServletRequest request, HttpSession session) {
    	String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		logger.debug("Received request to show change tech account access rqst page");
		
		
		String selection=null;		 
             if(request.getParameter("radios").equals("radio1")) {
            	 selection="Y";
            	 System.out.println("**********************************");
                 System.out.println("ALLOW TECHNICAL ACCOUNT ACCESS");
                 System.out.println("**********************************");
             }
             else
             {selection="N";
        	 System.out.println("*****************************************");
             System.out.println("DO NOT ALLOW TECHNICAL ACCOUNT ACCESS");
             System.out.println("*****************************************");
             }
             
             if(techAccountAccessService.setAccessFlag(userName, selection))
            	 	return "WelcomeMerchant";
             else
            	    return "MerchantTechAccountAccess";
	}
	
	
	@RequestMapping(value = "/MerchantTransfer", method = RequestMethod.GET)
    public String gettransfer(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
		if(error==true){
			model.put("error", "TRANSFER NOT SUCCESSFULL !!");	
		}else{
			model.put("error","");
		}
		
		logger.debug("Received request to show transfer page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "MerchantTransfer";
	}
	
	
	
	@RequestMapping(value = "/ViewMerchantTransactions", method = RequestMethod.GET)
    public String getviewTransactions(ModelMap model, HttpServletRequest request,HttpSession session ) {
		
		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		
		/*List<CustomertransactionFormBean> list=merchanttransactionFormBean.userAccounts(userName);
    	logger.debug("display "+list.size());
    	*/
    	model.put("mercAcc", merchantTransactionService.getTransactions(userName));
    	model.put("balance", merchantTransactionService.availableBalance(userName));
    	logger.debug("Received request to show employee user accounts page");
    
    	
    	return "ViewMerchantTransactions";
	}
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getwelcomeUser(ModelMap model, HttpServletRequest request,HttpSession session) {
    	logger.debug("Received request to show welcomeUser page");
    	
    	String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		
    
    	model.put("username", merchantTransactionService.getUserName(userName));
    	return "WelcomeMerchant";
	}
	
	
	
	
	@RequestMapping(value = "/WelcomeMerchant", method = RequestMethod.GET)
    public String getwelcomeUser1(ModelMap model, HttpServletRequest request,HttpSession session) {
    	logger.debug("Received request to show welcomeUser page");
    	
    	String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		
    
    	model.put("username", merchantTransactionService.getUserName(userName));
    	return "WelcomeMerchant";
	}
	
	
	
    
}
