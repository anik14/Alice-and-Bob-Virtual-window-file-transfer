/* Name: Mohammad Islam and TJ Cui
   Course: CSC 623
   Assignment: 5
   Date: 12/12/16
   Description: Bob class */


import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.cert.*;
import java.security.spec.*;
import java.io.*;
import cryptix.jce.provider.CryptixCrypto;


public class Bob
{

   static String              requestedEncryptionAlgorithm = "RSA";
   static int                 keyLength = 1024;


     
   public static void main(String[] args) throws Exception
   {
   
      try
      {
          
         Provider cp = new CryptixCrypto();
         Security.insertProviderAt(cp, 6);
         
          
         PublicKey alicePublickey = CryptoUtil.loadPublicKey(new File("alicePublicKey.txt"), requestedEncryptionAlgorithm);
         
         
         FileInputStream sign = new FileInputStream("sig");
         byte[] sigIn = new byte[sign.available()]; 
         sign.read(sigIn);
         sign.close();
         
         
         
         Signature s = Signature.getInstance("SHA1withRSA");
         s.initVerify(alicePublickey);
         
         
         FileInputStream ds = new FileInputStream(args[0]);
         BufferedInputStream bn = new BufferedInputStream(ds);
      
         byte[] br = new byte[1024];
         int len;
         while (bn.available() != 0) {
            len = bn.read(br);
            sig.update(br, 0, len);
         };
      
         bn.close();
         
         
         boolean verifies = sig.verify(signIn);
      
         System.out.println("signature verifies: " + verifies);
        
          
         PrivateKey bobPrivatekey= CryptoUtil.loadPrivateKey(new File("bobPrivateKey.txt"), requestedEncryptionAlgorithm);
          
                   Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS#1");
         cipher.init(Cipher.DECRYPT_MODE, bobPrivatekey);
      
      
                  byte[] cipherText = CryptoUtil.readCipherTextFile(new File(args[0]));
      
      
         
         String plaintext = new String(cipher.doFinal(cipherText));
         System.out.println(plaintext);
         
          
         FileOutputStream    fos;
         fos = new FileOutputStream("file2.txt");
         fos.write(plaintext.getBytes());
         fos.close();
      }
      
      catch (Exception e)
      {e.printStackTrace();}
   }

}


