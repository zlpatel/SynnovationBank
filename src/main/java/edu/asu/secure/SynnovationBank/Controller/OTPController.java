package edu.asu.secure.SynnovationBank.Controller;

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

import edu.asu.secure.SynnovationBank.FormBean.ChangePasswordFormBean;
import edu.asu.secure.SynnovationBank.FormBean.ForgotPasswordFormBean;
import edu.asu.secure.SynnovationBank.FormBean.OTPFormBean;
import edu.asu.secure.SynnovationBank.Service.OTPService;

@Controller
@RequestMapping("/otp")
public class OTPController {

	@Autowired
	private OTPService otpService;
	
	protected static Logger logger = Logger.getLogger("controller");
	
	
	@RequestMapping(value = "/forgotpassword", method= RequestMethod.GET)
    public String getForgotPasswordPage(@RequestParam(value="error", required=false) boolean error,ModelMap model ) {
		
		if(error==true){
			model.put("error", "Wrong Username/email");	
		}else{
			model.put("error","");
		}
		logger.debug("Received request to show forgotpassword page");
    	return "forgotpasswordpage";
	}
	
//	@RequestMapping(value = "/otprequest", method = RequestMethod.GET)
//    public String getOTPPage(ModelMap model) {
//
//		model.put("error", "The OTP you have entered is not correct!");
//		return "otprequestpage";
//	}
	
	@RequestMapping(value = "/otprequest", method = {RequestMethod.POST,RequestMethod.GET})
    public String getOTPPage(@RequestParam(value="error", required=false) boolean error,@ModelAttribute("forgotPasswordFormBean")
    ForgotPasswordFormBean forgotPasswordFormBean, BindingResult result,ModelMap model,HttpSession session) {

		if(error==true){
			model.put("error", "The OTP you have entered is not correct!");
		}else{
			model.put("error","");
			if(otpService.validateUser(forgotPasswordFormBean.getUsername(),forgotPasswordFormBean.getEmail())){
				
	            if (session != null) {
	                session.setAttribute("USERNAME", forgotPasswordFormBean.getUsername());
	            }
				logger.debug("Received request to show otp page");
			}else{
				model.put("error", true);
				return "redirect:forgotpassword";
			}
		}
		return "otprequestpage";
    	
	}
	
	@RequestMapping(value = "/changepassword", method = {RequestMethod.POST,RequestMethod.GET})
    public String getChangePasswordPage(@RequestParam(value="error", required=false) boolean error,@ModelAttribute("otpformbean")
    OTPFormBean otpFormBean, BindingResult result,ModelMap model, HttpSession session) {
		if(error==true){
			model.put("error", "Passwords doesn't match!");	
		}else{
			model.put("error","");
				if(otpService.compareOTP((String)session.getAttribute("USERNAME"),otpFormBean.getOtp())){
					
					logger.debug("Received request to show changepassword page");
			    	
				}
				else{
					model.put("error",true);
					logger.debug("entered OTP is not correct");
					return "redirect:otprequest";
				}
			
		}
		return "changepasswordpage";
    	
	}
	
	@RequestMapping(value = "/changepasswordsuccessful")
    public String getChangePasswordSuccessfulPage(@ModelAttribute("changepasswordformbean")
    ChangePasswordFormBean changePasswordFormBean, BindingResult result,ModelMap model) {
		
		if(otpService.updatePassword(changePasswordFormBean.getNewpassword(),changePasswordFormBean.getRetypepassword(),changePasswordFormBean.getUsername())){
			logger.debug("Received request to show changepasswordsuccessful page");
	    	return "changepasswordsuccessfulpage";
		}else{
			model.put("error",true);
			logger.debug("password doesn't match!");
			return "redirect:changepassword";
		}
		
	}
	

}
