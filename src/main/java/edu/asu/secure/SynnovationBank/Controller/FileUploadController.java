package edu.asu.secure.SynnovationBank.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.asu.secure.SynnovationBank.FormBean.FileUploadFormBean;

@Controller
@RequestMapping("/pki")
public class FileUploadController {

	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/fileUploader", method = RequestMethod.GET)
	public String getUploadFilePage(@RequestParam(value="error", required=false) boolean error,ModelMap model ) {
		
		if(error==true){
			model.put("error", "File is not uploaded");	
		}else{
			model.put("error","");
		}
		logger.debug("Received request to show fileUpload page");
    	return "fileUpload";
	}
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(@ModelAttribute("fileuploadformbean")FileUploadFormBean fileUploadFormBean,BindingResult result,ModelMap model ) {
		MultipartFile file=fileUploadFormBean.getFile();
		String name=fileUploadFormBean.getName();
		
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
 
                return "redirect:auth/login";
            } catch (Exception e) {
            	model.put("error", true);
                return "redirect:fileUploader";
            }
        } else {
        	model.put("error", true);
            return "redirect:fileUploader";
        }
		
    	
	}
}
