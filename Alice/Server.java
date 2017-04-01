/* Name: Mohammad Islam and TJ Cui
   Course: CSC 623
   Assignment: 5
   Date: 12/12/16
   Description: Server class */


import java.io.*;
import java.net.*;

public class Server { 
   public static void main (String [] args ) throws IOException { 
      ServerSocket sctServ = new ServerSocket(31000);
   
  
      Socket skt = sctServ.accept(); 
      System.out.println("Connection built: " + skt);
 
      File mtFile = new File ("signature"); 
      byte [] btar = new byte [(int) mtFile.length()]; 
      FileInputStream fis = new FileInputStream(mtFile); 

      BufferedInputStream bis = new BufferedInputStream(fis); 
      bis.read(btar,0,btar.length); 
      OutputStream ops = skt.getOutputStream(); 

      System.out.println("Sending "); 

      ops.write(btar,0,btar.length); 
      ops.flush(); skt.close(); 

      System.out.println("Finished transferring"); 
   } 
}

