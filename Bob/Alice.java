/* Name: Mohammad Islam and TJ Cui
   Course: CSC 623
   Assignment: 5
   Date: 12/12/16
   Description: Alice class */

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.cert.*;
import java.security.spec.*;
import java.io.*;
import cryptix.jce.provider.CryptixCrypto;




public class Alice
{

   static FileOutputStream    fos;
   static String              requestedEncryptionAlgorithm = "RSA";
   static int                 keyLength = 1024;


     
   public static void main(String[] args) throws Exception
   {
   
      try
      {
        
         Provider cp = new CryptixCrypto();
         Security.insertProviderAt(cp, 6);
      
         PublicKey bobPublicKey = CryptoUtil.loadPublicKey(new File("bobPublicKey.txt"), requestedEncryptionAlgorithm);
        
          
         Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS#1");
         cipher.init(Cipher.ENCRYPT_MODE, bobPublicKey);
      
      
          
         String textFile = CryptoUtil.readPlainTextFile(new File(args[0]));

      
      
         byte[] cipherText = cipher.doFinal(textFile.getBytes());
         
      
        PrivateKey alicePrivatekey= CryptoUtil.loadPrivateKey(new File("alicePrivateKey.txt"), requestedEncryptionAlgorithm);
        
         Signature RSA = Signature.getInstance("SHA1withRSA"); 
      
         
       
         RSA.initSign(alicePrivatekey);
         
         
         byte[] message = cipherText;
         RSA.update(message);
         System.out.println("before sign?:"+message.toString());
         
        
         byte[] sign = RSA.sign();
         RSA.update(message);
         System.out.println("after sign:"+message.toString());
      
         
         FileOutputStream sfos = new FileOutputStream("sig");
         sfos.write(sign);
         sfos.close();
         
         FileOutputStream mfos = new FileOutputStream("message");
         mfos.write(message);
         mfos.close();
                 }
      
      catch (Exception e)
      {e.printStackTrace();}
   }

}

