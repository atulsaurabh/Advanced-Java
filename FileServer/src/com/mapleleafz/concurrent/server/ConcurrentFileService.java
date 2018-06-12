package com.mapleleafz.concurrent.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConcurrentFileService implements Runnable 
{
   private Socket client;
   private static final String directory_path = "/home/atul_saurabh/Programmings/files/";
  
   public ConcurrentFileService(Socket client) {
		this.client = client;
	}
	@Override
	public void run() {
		try 
		{
			InputStream is = client.getInputStream();
			OutputStream os = client.getOutputStream();
			
			PrintWriter out = new PrintWriter(os,true);
			Scanner scanner = new Scanner(is) ;
			String file_name = scanner.nextLine();
			
			File file = new File(directory_path+file_name);
			
			if(file.exists())
			{
				BufferedInputStream file_stream = new BufferedInputStream(new FileInputStream(file));
				byte [] buffer = new byte[1024];
				while(file_stream.read(buffer) != -1)
				{
					os.write(buffer);
					os.flush();
				}
				
				file_stream.close();
				client.close();
				
			}
			else
			{
				out.println("File Is Not Available");
			}
			
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    
}
