package com.mapleleafz.concurrent.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try 
		{
		   ServerSocket serverSocket = new ServerSocket(7777);
		   System.out.println("Server Is Ready");
		   while(true)
		   {
			   Socket client = serverSocket.accept();
			   Thread thread = new Thread(new ConcurrentFileService(client));
			   thread.start();
		   }
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
