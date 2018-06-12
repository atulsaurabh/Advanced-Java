package com.mapleleafz.client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
	
   private static final String directory = "/home/atul_saurabh/Programmings/repo/";	
   public static void main(String[] args) 
   {
	  try 
	  {
		  Socket server = new Socket("localhost", 7777);
		  InputStream is = server.getInputStream();
		  OutputStream os = server.getOutputStream();
		  PrintWriter pw=new PrintWriter(os,true);
		  Scanner scanner = new Scanner(System.in);
		  System.out.println("Enter The File Name:");
		  String file_name = scanner.nextLine();
		  pw.println(file_name);
		  BufferedOutputStream bos = new BufferedOutputStream(
				  new FileOutputStream(new File(directory+file_name)));
		  
		  byte[] buffere = new byte[1024];
		  
		  while(is.read(buffere) != -1)
		  {
			  bos.write(buffere);
			  bos.flush();
		  }
		  
		  bos.close();
		  server.close();
		  
		  
	  } 
	  catch (IOException e) 
	  {
		e.printStackTrace();
	  }
   }
}
