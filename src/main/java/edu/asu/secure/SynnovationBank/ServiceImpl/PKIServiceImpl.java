package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;

import javax.security.cert.X509Certificate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import edu.asu.secure.SynnovationBank.Service.PKIService;

@Service
@Transactional
public class PKIServiceImpl implements PKIService {

	@Override
	public boolean verifyCertificate(MultipartFile file, String username) {

		boolean errorFlag=false;
		InputStream inStream = null;
		 try {
			 CertificateFactory cf = CertificateFactory.getInstance("X.509");
			  inStream=file.getInputStream();
				Certificate cert = cf.generateCertificate(inStream);
			     byte[] certData=cert.getEncoded();
			     X509Certificate c = X509Certificate.getInstance(certData);
			     
			     KeyStore ks = null;
					try{
						ks = KeyStore.getInstance("JKS");
					
					 
					 java.io.FileInputStream fis = null; try { fis = new
					 java.io.FileInputStream("/Users/zeel/sb_keystore.jks"); 
					 ks.load(fis,"123456".toCharArray()); 
					 }  
					 
					 finally {
						 if (fis != null) {
					 }
					 fis.close(); }	
				}catch(Exception e){
					System.out.println("In loadKeyStore exception : "+e);
				}
					try{
						if(ks.containsAlias(username)){
							Certificate pub = ks.getCertificate(username);
							byte[] pubCertData=pub.getEncoded();
						     X509Certificate pubCert = X509Certificate.getInstance(pubCertData);
						     c.verify(pubCert.getPublicKey());
						}
					} catch (KeyStoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						errorFlag=true;
					} catch (CertificateEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						errorFlag=true;
					} catch (InvalidKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						errorFlag=true;
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						errorFlag=true;
					} catch (NoSuchProviderException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						errorFlag=true;
					} catch (SignatureException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						errorFlag=true;
					}
					

		     
		 }catch(Exception certException){
			 certException.printStackTrace();
			 errorFlag=true;
		 }
		 finally {
		     if (inStream != null) {
		         try {
					inStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					errorFlag=true;
				}
		     }
		 }
		 
		 return errorFlag?false:true;
		
	}
}
