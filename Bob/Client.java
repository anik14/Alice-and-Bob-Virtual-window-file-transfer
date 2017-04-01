/* Name: Mohammad Islam and TJ Cui
   Course: CSC 623
   Assignment: 5
   Date: 12/12/16
   Description: Client class */



import java.io.*;
import java.net.*;
public class Client { 
   public static void main (String [] args ) 
   throws IOException { int filesize=2022386; 
      int brd; 
      int ctr = 0;
   
   
   
      Socket skt = new Socket ("192.168.56.101",31000); 

      byte [] btar = new byte [filesize]; 
      InputStream ist = skt.getInputStream(); 
      FileOutputStream fit = new FileOutputStream("sign");
      BufferedOutputStream bit = new BufferedOutputStream(fit); 
      brd = ist.read(btar,0,btar.length); 
      ctr = brd; 
      do { brd = ist.read(btar, ctr, (btar.length-ctr)); 
         if(brd >= 0) ctr += brd; } while(brd > -1); 
      bit.write(btar, 0 , ctr); 
      bit.flush(); 
      bit.close(); 
      skt.close(); 
   } 
}

//Read more: http://mrbool.com/file-transfer-between-2-computers-with-java/24516#ixzz4SW2zLouU