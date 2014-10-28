package edu.asu.secure.SynnovationBank.Controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.secure.SynnovationBank.FormBean.ChangePasswordFormBean;
import edu.asu.secure.SynnovationBank.FormBean.ForgetPasswordFormBean;
import edu.asu.secure.SynnovationBank.FormBean.OTPFormBean;
import edu.asu.secure.SynnovationBank.Handler.OTPGeneratorHandler;

@Controller
@RequestMapping("/otp")
public class OTPController {

	protected static Logger logger = Logger.getLogger("controller");
	
	//TODO zeel: remove it while implementing the actual mechanism
	public static String otpTemp="";
	
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
    public String getForgetPasswordPage() {
    	logger.debug("Received request to show forgetpassword page");
    	return "forgetpasswordpage";
	}
	
	@RequestMapping(value = "/otprequest", method = RequestMethod.POST)
    public String getOTPPage(@ModelAttribute("forgetpasswordformbean")
    ForgetPasswordFormBean forgetPasswordFormBean, BindingResult result,ModelMap model) {
		System.out.println("username: "+forgetPasswordFormBean.getUsername() +" , email: "+forgetPasswordFormBean.getEmail());
		otpTemp=OTPGeneratorHandler.GenerateOTP();
		//TODO zeel: pass the email id which will be retrieved from the Database and the OTP which is generated by the OTPGenerator
		OTPGeneratorHandler.SendOTP(forgetPasswordFormBean.getEmail(), otpTemp);
    	logger.debug("Received request to show otp page");
    	return "otprequestpage";
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String getChangePasswordPage(@ModelAttribute("otpformbean")
    OTPFormBean otpFormBean, BindingResult result,ModelMap model) {
		
		System.out.println("OTP : "+otpFormBean.getOtp());
		
		if(otpFormBean.getOtp().equals(otpTemp)){
			logger.debug("Received request to show changepassword page");
	    	return "changepasswordpage";
		}
		else{
			logger.debug("entered OTP is not correct");
			return "changepassword";
		}
    	
	}
	
	@RequestMapping(value = "/changepasswordsuccessful")
    public String getChangePasswordSuccessfulPage(@ModelAttribute("changepasswordformbean")
    ChangePasswordFormBean changePasswordFormBean, BindingResult result,ModelMap model) {
		
		System.out.println("New Password : "+changePasswordFormBean.getUsername() +" : "+changePasswordFormBean.getNewpassword());
    	logger.debug("Received request to show changepasswordsuccessful page");
    	return "changepasswordsuccessfulpage";
	}
}
