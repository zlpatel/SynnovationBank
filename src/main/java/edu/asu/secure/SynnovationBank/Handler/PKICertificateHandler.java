package edu.asu.secure.SynnovationBank.Handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import edu.asu.secure.SynnovationBank.ServiceImpl.SendMailTLS;


public class PKICertificateHandler {

public static void genCert(String commonName,String uName){
try {

String strCom = "keytool -genkeypair -dname \"cn="+commonName+", ou=Java, o=Oracle, c=US\" -alias "+uName+" -keypass 123456 -keystore C:/keystore/sb_synnovation.jks -storepass 123456 -validity 180";

Process p = Runtime.getRuntime().exec(strCom);
Thread.sleep(5000);
p.destroy();
System.out.println("process done:"+strCom);

}
catch (IOException | InterruptedException e) {
e.printStackTrace();
}

FileOutputStream cos=null;
java.io.FileInputStream fis=null;
try {
KeyStore kStore = null;
kStore = KeyStore.getInstance("JKS");
fis = new java.io.FileInputStream("C:/keystore/sb_synnovation.jks");
try {
kStore.load(fis, "123456".toCharArray());

} catch (NoSuchAlgorithmException e) {

e.printStackTrace();
} catch (CertificateException e) {

e.printStackTrace();
}
System.out.println("hello");

cos = new FileOutputStream("C:/keystore/"+uName+"Cert.cer");

java.security.cert.Certificate pub = kStore.getCertificate(uName);

cos.write(pub.getEncoded());


// updateKStore(kStore);
//pub.verify(pub.getPublicKey());

}catch(Exception e){
e.printStackTrace();
}
finally{
try {
cos.close();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
try {
fis.close();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
}


public static void sendCertificate(String alias,String emailId){
String filePath="C:/keystore/";
String fileName=alias+"Cert.cer";

SendMailTLS mailService=new SendMailTLS();
mailService.sendMailWithAttachment(emailId, " Please store it in a safer place. ", filePath+File.separator+fileName,fileName,"PKI");

}
}