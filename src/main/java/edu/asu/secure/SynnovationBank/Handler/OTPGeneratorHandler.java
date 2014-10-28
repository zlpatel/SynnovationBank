package edu.asu.secure.SynnovationBank.Handler;

import java.util.UUID;
import edu.asu.secure.SynnovationBank.ServiceImpl.SendMailTLS;

public class OTPGeneratorHandler {
	
	public static String GenerateOTP(){
		Long uuid = UUID.randomUUID().getLeastSignificantBits();
		if(uuid < 0) {
		uuid = uuid * -1;
		}
		return new String(uuid.toString());
	}
	
	
	public static void SendOTP(String emailId,String generatedOTP){
		SendMailTLS mailService=new SendMailTLS();
		mailService.sendEmail(emailId,generatedOTP,"OTP");
	}
}
