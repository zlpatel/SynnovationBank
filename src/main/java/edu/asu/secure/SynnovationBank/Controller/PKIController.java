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

import edu.asu.secure.SynnovationBank.FormBean.FileUploadFormBean;
import edu.asu.secure.SynnovationBank.Service.PKIService;

@Controller
@RequestMapping("/pki")
public class PKIController {

	protected static Logger logger = Logger.getLogger("controller");

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
	@RequestMapping(value = "/uploadfile")
	public String uploadFileHandler(@ModelAttribute("fileuploadformbean") FileUploadFormBean fileUploadFormBean,HttpSession session,BindingResult result,ModelMap model) {

		
		if(pkiService.verifyCertificate(fileUploadFormBean.getFile(),(String)session.getAttribute("USERNAME"))){
			return "changepasswordsuccessfulpage";
		}
		else{
			model.put("error",true);
			return "redirect:fileUploader";
		}
	}
}
