package edu.asu.secure.SynnovationBank.Handler;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;

import javax.security.cert.X509Certificate;
 
public class JavaRunCommand {
 
    public static void main(String args[]) {
 
        String s = null;
 
        try {
        	
        	String strCom = "keytool -genkeypair -dname \"cn=admin, ou=Java, o=Oracle, c=US\" -alias user1 -keypass 123456 -keystore /Users/zeel/sb_keystore.jks -storepass 123456 -validity 180";
        	String strCom2 = "keytool -genkeypair -dname \"cn=Rohit Kharat, ou=Java, o=Oracle, c=US\" -alias user2 -keypass 123456 -keystore /Users/zeel/sb_keystore.jks -storepass 123456 -validity 180";
             
        // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(strCom);
            Process p2 = Runtime.getRuntime().exec(strCom2);
             
            
        }
        catch (IOException e) {
            
        }
    	
    	/*KeyStore ks = null;
		try{
			ks = KeyStore.getInstance("JKS");
		
		 
		 java.io.FileInputStream fis = null; 
		 
		 try 
		 { 
			 fis = new java.io.FileInputStream("C:/Program Files/Java/jre7/bin/SBSG2.jks"); 
			 ks.load(fis,"123456".toCharArray());
			 
			 FileOutputStream kos = new FileOutputStream("C:/Users/SAMARTH/tmkKey2.pfx");
			 Key pvt = ks.getKey("SBSG2", "123456".toCharArray());
			 kos.write(pvt.getEncoded());
			 kos.flush();
			 kos.close();
			 
			 
			 FileOutputStream cos = new FileOutputStream("C:/Users/SAMARTH/tmkCert2.cer");
			 //Key pvt = ks.getKey("SBSG2", "123456".toCharArray());
			 Certificate pub = ks.getCertificate("SBSG2");
			 
			 cos.write(pub.getEncoded());
			 cos.flush();
			 cos.close();
			 
			 
			 KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection("parsam".toCharArray());
			 	SecureRandom random = null;
				random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			 
				KeyPairGenerator keyGen = null;
				keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
				keyGen.initialize(1024, random);
				
				KeyPair keyPairUser = keyGen.generateKeyPair();
				KeyStore.PrivateKeyEntry privateKeyEntry = new KeyStore.PrivateKeyEntry(keyPairUser.getPrivate());
			     ks.setEntry("SBSG2", privateKeyEntry, passwordProtection);
			 
			 //Certificate c = ks.getCertificate(ks.aliases().nextElement());
		 
			//File file = new File("C:/Users/SAMARTH/mycertificate.cer");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fout = new FileOutputStream(file);
			 ObjectOutputStream oos = new ObjectOutputStream(fout);
			 oos.writeObject(c);
			 
			 FileInputStream fin = new FileInputStream(file);
			 ObjectInputStream ois = new ObjectInputStream(fin);
			 Certificate c1 = (Certificate) ois.readObject();
			 
			 System.out.println("retrieved: "+pub);*/
			   
    	

        /*Certificate c = new Certificate("my cert1") {
			
			@Override
			public void verify(PublicKey key, String sigProvider)
					throws CertificateException, NoSuchAlgorithmException,
					InvalidKeyException, NoSuchProviderException, SignatureException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void verify(PublicKey key) throws CertificateException,
					NoSuchAlgorithmException, InvalidKeyException,
					NoSuchProviderException, SignatureException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public PublicKey getPublicKey() {
				// TODO Auto-generated method stub
				try {
					SecureRandom random = null;
					random = SecureRandom.getInstance("SHA1PRNG", "SUN");
					KeyPairGenerator keyGen = null;
					keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
					keyGen.initialize(1024, random);
					//KeyPair keyPairBank = keyGen.generateKeyPair();
					KeyPair keyPairUser = keyGen.generateKeyPair();
					return keyPairUser.getPublic();
				}
				catch (Exception e) {
					System.out.println("In else exception : "+e );// TODO: handle exception
				}
				
				return null;
				
			}
			
			@Override
			public byte[] getEncoded() throws CertificateEncodingException {
				// TODO Auto-generated method stub
				return null;
			}
		};
			System.out.println("Done");
			System.out.println("cert pk1:"+c.getPublicKey());
			
			*/
    	
    	KeyStore ks = null;
		try{
			ks = KeyStore.getInstance("JKS");
		
		 
		 java.io.FileInputStream fis = null; try { fis = new
		 java.io.FileInputStream("/Users/zeel/sb_keystore.jks"); 
		 ks.load(fis,"123456".toCharArray()); }  
		 
		 finally {
			 if (fis != null) {
		 }
		 fis.close(); }	
	}catch(Exception e){
		System.out.println("In loadKeyStore exception : "+e);
	}
		
		try { int i=0;
		 Enumeration aliasList = ks.aliases(); 
		 
		//delete entry
		 while(aliasList.hasMoreElements()) {
			 System.out.println("Inside delete:");
			 //ks.deleteEntry(aliasList.nextElement().toString());
			 aliasList.nextElement();
			 }
			System.out.println(ks.containsAlias("admin"));
			System.out.println(ks.containsAlias("user2"));
			
			System.out.println(ks.getCertificate(ks.aliases().nextElement()));
			
			FileOutputStream cos = new FileOutputStream("/Users/zeel/user1Cert.cer");
			 //Key pvt = ks.getKey("SBSG2", "123456".toCharArray());
			 Certificate pub = ks.getCertificate("admin");
			 Certificate pub2 = ks.getCertificate("user2");
			 
			 cos.write(pub.getEncoded());
			 FileOutputStream cos2 = new FileOutputStream("/Users/zeel/user2Cert.cer");
			 cos2.write(pub2.getEncoded());
			 
			 pub2.verify(pub2.getPublicKey());
			 
			 InputStream inStream = null;
			 try {
			     inStream = new FileInputStream("/Users/zeel/user1Cert.cer");
			     CertificateFactory cf = CertificateFactory.getInstance("X.509");
			     Certificate cert = cf.generateCertificate(inStream);
			     byte[] certData=cert.getEncoded();
			     X509Certificate c = X509Certificate.getInstance(certData);
			     System.out.println("x509 id:"+c.getIssuerDN());
			     
			 }catch(Exception certException){
				 certException.printStackTrace();
			 }
			 finally {
			     if (inStream != null) {
			         inStream.close();
			     }
			 }
			 
			 
			 
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
   
		
    }
