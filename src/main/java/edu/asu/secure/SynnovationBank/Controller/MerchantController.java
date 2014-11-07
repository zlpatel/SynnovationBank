package edu.asu.secure.SynnovationBank.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.FormBean.CreditFormBean;
import edu.asu.secure.SynnovationBank.FormBean.DebitFormBean;
import edu.asu.secure.SynnovationBank.FormBean.FileUploadFormBean;
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
import edu.asu.secure.SynnovationBank.Service.PKIService;

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
	@Autowired
	private PKIService pkiService;
	
	/**
     * Handles and retrieves the merchant JSP page that only merchants can see
     * 
     * @return the name of the JSP page
     */

	@RequestMapping(value = "/changemerchantinforequest", method = RequestMethod.POST)
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
	

	@RequestMapping(value = "/merchanttechaccountaccess", method = RequestMethod.POST)
	public String env(HttpServletRequest request, HttpSession session){

		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		logger.debug("Received request to show change tech account access rqst page");
		
		
		boolean selection=false;		 
             if(request.getParameter("radios").equals("radio1")) {
            	 selection=true;
            	 System.out.println("**********************************");
                 System.out.println("ALLOW TECHNICAL ACCOUNT ACCESS");
                 System.out.println("**********************************");
             }
             else
             {selection=false;
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
/*	@RequestMapping(value = "/admintransactionaccepted/{userId}/{transactionId}/{notificationId}", method = {RequestMethod.POST, RequestMethod.GET})
    public String adminTransactionAccepted(@PathVariable("userId") String userId, @PathVariable("transactionId") Long transactionId, @PathVariable("notificationId") Long notificationId, ModelMap model, HttpServletRequest request){
    		
//        public String adminTransactionAccepted(@RequestParam(value="notification", required=true) AdminCriticalTransactionsFormBean notification, HttpServletRequest request,  
//                HttpServletResponse response, ModelMap model) {
    	
    	logger.debug("Received request to accept critical transaction");
    	
    	adminNotificationService.sendTransactionAcceptedNotification(userId, transactionId, notificationId);
        	return "redirect:/secure/admin/admincriticaltransactions";
    }*/
	
	@RequestMapping(value = "/merchantacceptexternaluser/{userName}/{notification_id}/{transaction_id}", method =RequestMethod.POST)
    public String getMerchantAccept(@PathVariable("userName") String userName, @PathVariable("transaction_id") Long transaction_id, @PathVariable("notification_id") Long notification_id, ModelMap model, HttpServletRequest request) {

		
		logger.debug("Received request to show merchantsubmitpayment page");
		System.out.println("Accepted Notification :"+notification_id);		
		
		System.out.println(transaction_id);
		float amount=merchantNotificationService.getTransactionAmount(transaction_id);
		System.out.println("amount"+amount);
		MerchantPaymentFormBean merchantPaymentFormBean=new MerchantPaymentFormBean();
		merchantPaymentFormBean.setAmount(amount);
		merchantPaymentFormBean.setNotification_id(notification_id);
		merchantPaymentFormBean.setTransaction_id(transaction_id);
		
		model.put("merchantPaymentFormBean",merchantPaymentFormBean);
		
		return "MerchantSubmitPayment";
    	
	}
	
	//reject
	@RequestMapping(value = "/merchantrejectexternaluser/{userName}/{notification_id}", method = RequestMethod.POST)
    public String getMerchantReject(@PathVariable("userName") String userName, @PathVariable("notification_id") Long notification_id, ModelMap model, HttpServletRequest request) {

		logger.debug("Received request to reject merchantsubmitpayment page");
		System.out.println("Rejected Notification :"+notification_id);		
		
		merchantNotificationService.sendTransactionDeclinedNotification(userName,notification_id);
		return "MerchantDisplayReject";
    	
	}
	
	/*
	 *@RequestMapping(value = "/uploadfile", method=RequestMethod.POST)
	public String uploadFileHandler(@ModelAttribute("fileuploadformbean") FileUploadFormBean fileUploadFormBean,HttpSession session,BindingResult result,ModelMap model) {

		if(pkiService.verifyCertificate(fileUploadFormBean.getFile(),(String)session.getAttribute("USERNAME"))){
			return "CertificateVerified";
		}
		else{
			model.put("error",true);
			return "redirect:fileUploader";
		}
	}
	
	*/
	//Controller for redirecting when accept notification  by merchant
		@RequestMapping(value = "/submitpayment/{notification_id}", method = RequestMethod.POST)
	    public @ResponseBody String getSubmitPayment(@ModelAttribute("merchantPaymentFormBean") MerchantPaymentFormBean merchantPaymentFormBean,ModelMap model, @PathVariable("notification_id") Long notification_id, HttpServletRequest request, HttpSession session) {

			logger.debug("Received request to show submit payment page");	
			
		    //verify PKI certificate
			if(pkiService.verifyCertificate(merchantPaymentFormBean.getFile(),(String)session.getAttribute("USERNAME"))){
				merchantNotificationService.sendTransactionAcceptedNotification(notification_id);
				return "MerchantDisplaySubmitPaymentResult";
				
			}
			
			else{
				return "Certificate invalid";
				
			
			}
			
			
		
	    	
		}
	
	
	@RequestMapping(value = "/MerchantSubmitPayment", method = RequestMethod.POST)
    public String submitPayment(@RequestParam(value="error", required=false) boolean error,ModelMap model) { 
	
		logger.debug("Received request to submit payment");
//    
//		if(error==true){
//			System.out.println("ERROR SUBMITTING THE PAYMENT");
//		}
//		else{
//			System.out.println("PAYMENT SUBMITTED SUCCESSFULLY");
//			
//		}
		
		
    	return "MerchantSubmitPayment";
	}
	
	@RequestMapping(value = "/MerchantAcceptNotification", method = {RequestMethod.POST, RequestMethod.GET})
	public String displayNotifications(ModelMap model, HttpServletRequest request,HttpSession session) {
    	logger.debug("Received request to show merchantAcceptNotification page");
    	
    	String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
        List<MerchantNotifFormBean> mnotif=merchantNotificationService.notifications(userName);
        
        model.put("merchantNotifFormBean", merchantNotificationService.notifications(userName));
		
    	return "MerchantAcceptNotification";
	}
	
	
	// controller for crediting
	
	@RequestMapping(value = "/merchantcreditrequest", method = RequestMethod.POST)
    public String getCreditRqstPage(@RequestParam(value="error", required=false) boolean error,ModelMap model, @ModelAttribute("creditFormBean") CreditFormBean creditFormBean, HttpServletRequest request, HttpSession session) {

		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		logger.debug("Received request to show creditrequest page");
		System.out.println("credited amount :"+creditFormBean.getCreditAmount()+"to Account:" +userName );
		
			if(creditService.creditAmount(userName,creditFormBean.getCreditAmount())){
				model.put("error","CREDIT SUCCESSFUL");
				return "WelcomeMerchant";
				
			}
				
			
			else
			{
				model.put("error","CREDIT UNSUCCESSFUL");
				return "MerchantCredit";
				
			}
    	
	}
	

	
	
	// controller for debiting
	
		@RequestMapping(value = "/merchantdebitrequest", method = RequestMethod.POST)
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
		
		@RequestMapping(value = "/merchanttransferrequest", method = RequestMethod.POST)
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
					return "MerchantTransfer";
			}
	    	
		}
	
	
	 @RequestMapping(value = "/MerchantDebit", method = {RequestMethod.POST, RequestMethod.GET})
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
		
	
	
	
	
	
	
    
    
    @RequestMapping(value = "/MerchantCredit", method = {RequestMethod.POST, RequestMethod.GET})
    public String getCreditDebit(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
    	if(error==true){
			model.put("error", "CREDIT NOT SUCCESSFULL !!");	
		}else{
			model.put("error","");
		}
    	logger.debug("Received request to show credit/debit page");
    
    	return "MerchantCredit";
	}
	
	
	
	@RequestMapping(value = "/ChangeMerchantInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public String getChangeMerchantInfo() {
    	logger.debug("Received request to show Change merchant information page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "ChangeMerchantInfo";
	}
	
	
	
	@RequestMapping(value = "/MerchantNotifications", method = {RequestMethod.POST, RequestMethod.GET})
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
	
	
	
	
	
	@RequestMapping(value = "/MerchantTechAccountAccess", method = {RequestMethod.POST, RequestMethod.GET})
    public String gettechAccountAccess(HttpServletRequest request, HttpSession session) {
//    	String userName="";
//		session = request.getSession(false);
//        if (session != null) {
//            userName=(String)request.getSession().getAttribute("USERNAME");
//        }
//		logger.debug("Received request to show change tech account access rqst page");
//		
//		
//		boolean selection=false;		 
//             if(request.getParameter("radios").equals("radio1")) {
//            	 selection=true;
//            	 System.out.println("**********************************");
//                 System.out.println("ALLOW TECHNICAL ACCOUNT ACCESS");
//                 System.out.println("**********************************");
//             }
//             else
//             {selection=false;
//        	 System.out.println("*****************************************");
//             System.out.println("DO NOT ALLOW TECHNICAL ACCOUNT ACCESS");
//             System.out.println("*****************************************");
//             }
//             
//             if(techAccountAccessService.setAccessFlag(userName, selection))
//            	 	return "WelcomeMerchant";
//             else
            	    return "MerchantTechAccountAccess";
	}
	
	
	@RequestMapping(value = "/MerchantTransfer", method = {RequestMethod.POST, RequestMethod.GET})
    public String gettransfer(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
		if(error==true){
			model.put("error", "TRANSFER NOT SUCCESSFULL !!");	
		}else{
			model.put("error","");
		}
		
		logger.debug("Received request to show transfer page");
    
    	return "MerchantTransfer";
	}
	
	
	
	@RequestMapping(value = "/ViewMerchantTransactions", method = {RequestMethod.POST, RequestMethod.GET})
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
	
	
	@RequestMapping(value = "/home", method = {RequestMethod.POST, RequestMethod.GET})
    public String getwelcomeUser(ModelMap model, HttpServletRequest request,HttpSession session) {
    	logger.debug("Received request to show welcomeUser page");
    	
    	String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		
        System.out.println("herre ---"+userName);
    	model.put("username", userName);
    	return "WelcomeMerchant";
	}
	
	
	
	
	@RequestMapping(value = "/WelcomeMerchant", method = {RequestMethod.POST, RequestMethod.GET})
    public String getwelcomeUser1(ModelMap model, HttpServletRequest request,HttpSession session) {
    	logger.debug("Received request to show welcomeUser page");
    	
    	String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		
    
    	model.put("username", userName);
    	return "WelcomeMerchant";
	}
	
	
	
    
}
