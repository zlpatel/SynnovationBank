package edu.asu.secure.SynnovationBank.Handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.Certificate;

import edu.asu.secure.SynnovationBank.ServiceImpl.SendMailTLS;


public class PKICertificateHandler {
	
	public static void generateCertificate(String commonName,String alias){
		try {
        	String keystorePath="C:/Users/MEGHNA/Desktop/KeyStore/sb_keystore.jks";
        	String strCom = "keytool -genkeypair -dname \"cn="+commonName+", ou=SynnovationBank, o=SynnovationBank, c=US\" -alias "+alias+" -keypass 123456 -keystore +"+keystorePath+" -storepass 123456 -validity 180";
        	 
        // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(strCom);
        }
        catch (IOException e) {
            
        }
	}
	
	public static void sendCertificate(String alias,String emailId){
		KeyStore ks = null;
		String filePath="C:/Users/MEGHNA/Desktop/Certificate/";
		String fileName=alias+"Cert.cer";
		try{
			ks = KeyStore.getInstance("JKS");
			
		 
		 java.io.FileInputStream fis = null; try { fis = new
		 java.io.FileInputStream("C:/Users/MEGHNA/Desktop/KeyStore/sb_keystore.jks"); 
		 ks.load(fis,"123456".toCharArray());}  
		 
		 finally {
			 if (fis != null) {
		 }
		 fis.close(); }	
		}catch(Exception e){
		System.out.println("In loadKeyStore exception : "+e);
		}
		FileOutputStream cos=null;
		try{
			if(ks.containsAlias(alias)){
				Certificate pub = ks.getCertificate(alias);
				cos = new FileOutputStream(filePath+File.separator+fileName);
			     cos.write(pub.getEncoded());
			     SendMailTLS mailService=new SendMailTLS();
					mailService.sendMailWithAttachment(emailId, "Please store it in a safer place.", filePath+File.separator+fileName,"PKI");
			}
			else{
				System.out.println("Certificate has not been found!");
				}
			}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				cos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
