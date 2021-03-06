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

import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.Dao.PersonDAO;
import edu.asu.secure.SynnovationBank.FormBean.CreditFormBean;
import edu.asu.secure.SynnovationBank.FormBean.CustomerInfoChangeFormBean;
import edu.asu.secure.SynnovationBank.FormBean.CustomertransactionFormBean;
import edu.asu.secure.SynnovationBank.FormBean.DebitFormBean;
import edu.asu.secure.SynnovationBank.FormBean.FileUploadFormBean;
import edu.asu.secure.SynnovationBank.FormBean.TechAccessFormBean;
import edu.asu.secure.SynnovationBank.FormBean.TransferFormBean;
import edu.asu.secure.SynnovationBank.Service.CreditService;
import edu.asu.secure.SynnovationBank.Service.CustomerInfoChangeService;
import edu.asu.secure.SynnovationBank.Service.CustomerNotificationService;
import edu.asu.secure.SynnovationBank.Service.CustomerTransactionService;
import edu.asu.secure.SynnovationBank.Service.DebitService;
import edu.asu.secure.SynnovationBank.Service.PKIService;
import edu.asu.secure.SynnovationBank.Service.TechAccountAccessService;
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
	@Autowired
	private CustomerInfoChangeService customerInfoChangeService;
	@Autowired
	private TechAccountAccessService techAccountAccessService;
	@Autowired
	private CustomerTransactionService customerTransactionService;
	@Autowired
	private CustomerNotificationService customerNotificationService; 
	@Autowired
	private PersonDAO personDao;
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
    
    
	
	
	
	
	@RequestMapping(value = "/paymerchantrqst", method = RequestMethod.GET)
    public String getMerchantTransferRqstPage(@RequestParam(value="error", required=false) boolean error, ModelMap model,@ModelAttribute("transferFormBean") TransferFormBean transferFormBean, HttpServletRequest request, HttpSession session) {
		
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
			
			return "welcomeUser";
		}
		else
		{
			model.put("error","TRANSFER UNSUCCESSFULL (or) PENDING FOR APPROVAL FROM ADMINISTRATOR --- CHECK ''VIEW TRANSACTIONS'' TAB TO SEE IF A TRANSACTION IS CREATED FOR YOUR REQUEST (Your account balance won't be updated until approval from bank admin)");
				return "transfer";
		}
    	
	}

	//merchant transer request
	// controller for 		transferrequest
	
			@RequestMapping(value = "/merchanttransferrequest", method = RequestMethod.GET)
		    public String getMerchantTransferRqstPage1(@RequestParam(value="error", required=false) boolean error, ModelMap model,@ModelAttribute("transferFormBean") TransferFormBean transferFormBean, HttpServletRequest request, HttpSession session) {
				
				String userName="";
				session = request.getSession(false);
		        if (session != null) {
		            userName=(String)request.getSession().getAttribute("USERNAME");
		        }

				logger.debug("Received request to show merchant payment rqst page");
				System.out.println("Send from:" +userName); 
				System.out.println("Send to :"+transferFormBean.getReceiverID());
				System.out.println("Transfer amount :"+transferFormBean.getTransferAmount());
					
				if(transferService.performTransfer(0,userName, transferFormBean.getReceiverID(),transferFormBean.getTransferAmount()))
				{
					
					return "welcomeUser";
				}
				else
				{
					model.put("error","TRANSFER UNSUCCESSFULL (or) PENDING FOR APPROVAL FROM ADMINISTRATOR --- CHECK ''VIEW TRANSACTIONS'' TAB TO SEE IF A TRANSACTION IS CREATED FOR YOUR REQUEST (Your account balance won't be updated until approval from bank admin)");
						return "transfer";
				}
		    	
			}
		

	
	@RequestMapping(value = "/changecustomerinforequest", method = RequestMethod.POST)
    public String getNewCustomerInfo(@ModelAttribute("customerInfoChangeFormBean") CustomerInfoChangeFormBean customerInfoChangeFormBean,ModelMap model, HttpServletRequest request, HttpSession session) {

		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		logger.debug("Received request to show customer info change rqst page");
		
		System.out.println("New information to be changed: "+ customerInfoChangeFormBean.getFirstName() + " "+customerInfoChangeFormBean.getLastName()+" "+customerInfoChangeFormBean.getEmail());
		
		String firstName=customerInfoChangeFormBean.getFirstName();
		String middleName=customerInfoChangeFormBean.getMiddleName();
		String lastName=customerInfoChangeFormBean.getLastName();
		String address=customerInfoChangeFormBean.getAddress();
		String email=customerInfoChangeFormBean.getEmail();
		
		if(firstName=="")
		{
			firstName=null;
			System.out.println("firstName: "+firstName+"\n");
			
		}
		if(middleName=="")
		{
			middleName=null;

			System.out.println("middleName: "+middleName+"\n");
		}
		if(lastName=="")
		{
			lastName=null;
			System.out.println("lastName: "+lastName+"\n");
		}
		
		if(address=="")
		{
			address=null;
			System.out.println("address: "+address+"\n");
		}
		if(email=="")
		{
			email=null;
			System.out.println("email: "+email+"\n");
		}
		
		
		
		if(customerInfoChangeService.changeCustomerInformation(userName, firstName, middleName,lastName,address,email))
		{
			
            model.put("error","Information Changed");
			return "changeCustomerInfo";
		}	
			
			
			else{
				model.put("error","Information Not Changed");
				return "changeCustomerInfo";
			}
	}
	


	@RequestMapping(value = "/techaccountaccess", method = RequestMethod.POST)
	public String env(HttpServletRequest request, HttpSession session, ModelMap model){

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
             
             if(techAccountAccessService.setAccessFlag(userName, selection)){
            	 model.put("error"," Flag set");
            	 return "techAccountAccess";
             }
             else{
            	 //model.put("error"," Flag Not set");
            	
            	    return "techAccountAccess";
             }

    }
	
	
	
	
	
	
	
	
	
	// controller for crediting
	
	@RequestMapping(value = "/creditrequest", method = RequestMethod.POST)
    public String getCreditRqstPage(@RequestParam(value="error", required=false) boolean error,ModelMap model, @ModelAttribute("creditFormBean") CreditFormBean creditFormBean, HttpServletRequest request, HttpSession session) {

		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		logger.debug("Received request to show creditrequest page");
		System.out.println("credited amount :"+creditFormBean.getCreditAmount()+"to Account:" +userName );
			if(creditService.creditAmount(userName,creditFormBean.getCreditAmount())){
				model.put("error","DEPOSIT SUCCESSFULL");
				return "credit_debit";
			}
			else
			{
				model.put("error","DEPOSIT UNSUCCESSFULL");
				return "credit_debit";
				
			}
    	
	}
	

	
	
	// controller for debiting
	
		@RequestMapping(value = "/debitrequest", method = RequestMethod.POST)
	    public String getDebitRqstPage(@RequestParam(value="error", required=false) boolean error,ModelMap model,@ModelAttribute("debitFormBean") DebitFormBean debitFormBean, HttpServletRequest request, HttpSession session) {

			String userName="";
			session = request.getSession(false);
	        if (session != null) {
	            userName=(String)request.getSession().getAttribute("USERNAME");
	        }
			
			logger.debug("Received request to show debitrequest page");
			System.out.println("Debited amount :"+debitFormBean.getDebitAmount()+"to Account:" +userName );
				if(debitService.debitAmount(userName, debitFormBean.getDebitAmount())){
					model.put("error","WITHDRAWAL SUCCESSFULL");
					return "debit";
				}
				else
				{
					model.put("error","WITHDRAWAL UNSUCCESSFULL");
					return "debit";
					
				}
	    	
		}

	
	
		
		

		
		@RequestMapping(value = "/criticalTransfer", method = RequestMethod.GET)
	    public String getcriticalTrans(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
			if(error==true){
				model.put("error", "TRANSFER NOT SUCCESSFULL !!");	
			}else{
				model.put("error","");
			}
			
			logger.debug("Received request to show merchant payment page");
	    
	    	// Do your work here. Whatever you like
	    	// i.e call a custom service to do your business
	    	// Prepare a model to be used by the JSP page
	    	
	    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
	    	return "fileUpload1";
		}
		
		
		
		
		
		
		
		
		
		
	
	
	

	
		// controller for 		transferrequest
		
		@RequestMapping(value = "/transferrequest", method = RequestMethod.POST)
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
			System.out.println("length of username = " + transferFormBean.getReceiverID().length());
			
			if(!(transferService.getReceiverRole(transferFormBean.getReceiverID())).equalsIgnoreCase("ROLE_MERC"))
			{
			if(transferService.performTransfer(userName, transferFormBean.getReceiverID(),transferFormBean.getTransferAmount()))
			{
				
				model.put("error","TRANSFER SUCCESSFULL");
				return "transfer";
				
			}
			
			else
			{
				model.put("error","TRANSFER UNSUCCESSFULL (or) PENDING FOR APPROVAL FROM ADMINISTRATOR --- CHECK ''VIEW TRANSACTIONS'' TAB TO SEE IF A TRANSACTION IS CREATED FOR YOUR REQUEST (Your account balance won't be updated until approval from bank admin)");
					return "transfer";
			}
			
			}else
			{
				model.put("error","Please Use Merchant Payment to pay to a Merchant");
				return "transfer";
			}
	    	
		}

		
		
		
		@RequestMapping(value = "/transferrequest1", method = RequestMethod.POST)
	    public String getTransferRqstPage1(@RequestParam(value="error", required=false) boolean error, ModelMap model,@ModelAttribute("transferFormBean") TransferFormBean transferFormBean, HttpServletRequest request, HttpSession session) {
			
			String userName="";
			session = request.getSession(false);
	        if (session != null) {
	            userName=(String)request.getSession().getAttribute("USERNAME");
	        }

			logger.debug("Received request to show critial transfer rqst page");
			System.out.println("Send from:" +userName); 
			System.out.println("Send to :"+transferFormBean.getReceiverID());
			System.out.println("Transfer amount :"+transferFormBean.getTransferAmount());
			Person person=personDao.fetchUserById("merchant");
		System.out.println(person);
		String role=person.getRole();
		System.out.println(role);
		if(!person.getRole().equals("ROLE_MERC")){
			if(transferService.performTransfer(1,userName, transferFormBean.getReceiverID(),transferFormBean.getTransferAmount()))
			{
				
				model.put("error","TRANSFER SUCCESSFULL");
				return "transfer";
			}
			else
			{
				model.put("error","TRANSFER UNSUCCESSFULL (or) PENDING FOR APPROVAL FROM ADMINISTRATOR --- CHECK ''VIEW TRANSACTIONS'' TAB TO SEE IF A TRANSACTION IS CREATED FOR YOUR REQUEST (Your account balance won't be updated until approval from bank admin)");
					return "transfer";
			}
			}else
			{
				model.put("error","Use Merchant Payment to pay to merchan//t)");
				return "transfer";
			}
	    	
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	 @RequestMapping(value = "/debit", method = RequestMethod.GET)
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
	    	return "debit";
		}
		
	
	
	
	
	
	
    
    
    @RequestMapping(value = "/credit_debit", method = RequestMethod.GET)
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
    public String getCustomerNotifications(ModelMap model, HttpServletRequest request, HttpSession session) {
		
		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		
		model.put("custNotifFormBean", customerNotificationService.notifications(userName));
        
		
		
		
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
    	return "transfer";
	}
	
	
	
	
	@RequestMapping(value = "/payMerchant", method = RequestMethod.GET)
    public String getMerchantPage1(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
		if(error==true){
			model.put("error", "TRANSFER NOT SUCCESSFULL !!");	
		}else{
			model.put("error","");
		}
		
		logger.debug("Received request to show merchant payment page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "fileUpload";
	}
	
	
	
	
	
	@RequestMapping(value = "/payMerchantActual", method = RequestMethod.GET)
    public String getMerchantPage(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
		if(error==true){
			model.put("error", "TRANSFER NOT SUCCESSFULL !!");	
		}else{
			model.put("error","");
		}
		
		logger.debug("Received request to show merchant payment page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "payMerchant";
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/viewTransactions", method = RequestMethod.GET)
    public String getviewTransactions(ModelMap model, HttpServletRequest request,HttpSession session ) {
		
		String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		
		/*List<CustomertransactionFormBean> list=customertransactionFormBean.userAccounts(userName);
    	logger.debug("display "+list.size());
    	*/
    	model.put("custAcc", customerTransactionService.getTransactions(userName));
    	model.put("balance", customerTransactionService.availableBalance(userName));
    	logger.debug("Received request to show employee user accounts page");
    
    	
    	return "viewTransactions";
	}
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getwelcomeUser(ModelMap model, HttpServletRequest request,HttpSession session) {
    	logger.debug("Received request to show welcomeUser page");
    	
    	String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		
    
    	model.put("username", userName);
    	return "welcomeUser";
	}
	
	
	
	
	@RequestMapping(value = "/welcomeUser", method = RequestMethod.GET)
    public String getwelcomeUser1(ModelMap model, HttpServletRequest request,HttpSession session) {
    	logger.debug("Received request to show welcomeUser page");
    	
    	String userName="";
		session = request.getSession(false);
        if (session != null) {
            userName=(String)request.getSession().getAttribute("USERNAME");
        }
		
    
    	model.put("username", customerTransactionService.getUserName(userName));
    	return "welcomeUser";
	}
	
	
	
	
    
    
    
	
	
	@Autowired
	private PKIService pkiService;
	
	@RequestMapping(value = "/fileUploader" ,method = RequestMethod.GET)
	public String getUploadFilePage(@RequestParam(value="error", required=false) boolean error,ModelMap model ) {
		
		if(error==true){
			model.put("error", "Certificate is not valid");	
		}else{
			model.put("error","");
		}
		logger.debug("Received request to show fileUpload page");
    	return "fileUpload";
	}
	@RequestMapping(value = "/uploadfile", method=RequestMethod.POST)
	public String uploadFileHandler(@ModelAttribute("fileuploadformbean") FileUploadFormBean fileUploadFormBean,HttpSession session,BindingResult result,ModelMap model) {

		
		// just to check the flow go to paymerchant page
		
		//include this service layer call later
		
		if(pkiService.verifyCertificate(fileUploadFormBean.getFile(),(String)session.getAttribute("USERNAME"))){
			return "payMerchantActual";
		}
		else{
			model.put("error",true);
			return "redirect:fileUploader";
		}
	}

	
    
    
	@RequestMapping(value = "/fileUploader1" ,method = RequestMethod.GET)
	public String getUploadFilePage1(@RequestParam(value="error", required=false) boolean error,ModelMap model ) {
		
		if(error==true){
			model.put("error", "Certificate is not valid");	
		}else{
			model.put("error","");
		}
		logger.debug("Received request to show fileUpload page");
    	return "fileUpload1";
	}
	
	
	
	
	
	@RequestMapping(value = "/uploadfile1", method=RequestMethod.POST)
	public String uploadFileHandler1(@ModelAttribute("fileuploadformbean") FileUploadFormBean fileUploadFormBean,HttpSession session,BindingResult result,ModelMap model) {




	if(pkiService.verifyCertificate(fileUploadFormBean.getFile(),(String)session.getAttribute("USERNAME"))){
	return "transfercrit";
	}
	else{
	model.put("error",true);
	return "redirect:fileUploader";
	}
	}
	
	@RequestMapping(value = "/enablejavascript", method =  RequestMethod.GET)
    public String getEnableJavascriptPage(ModelMap model, HttpServletRequest request){
    		
//        public String adminTransactionAccepted(@RequestParam(value="notification", required=true) AdminCriticalTransactionsFormBean notification, HttpServletRequest request,  
//                HttpServletResponse response, ModelMap model) {
    	
    	
        	return "EnableJavascript";
    }


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
    
    
    
}
