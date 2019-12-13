package main.lisp.distributed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientModel {
	private String serverHostName;
	private Socket clientEnd = null;
	private PrintWriter clientOut;
	private BufferedReader clientIn;
	public ClientModel(String aServerHostName, int portNumber) {
		serverHostName = aServerHostName;
		  try {
		    clientEnd = new Socket(serverHostName, 
		                portNumber);
		    System.out.println("Connected to server:" + clientEnd);
		    clientOut = new PrintWriter(
			clientEnd.getOutputStream(), true);
		    clientIn = new BufferedReader(
			new InputStreamReader(clientEnd.getInputStream()));
		  } catch (IOException e) {
		    e.printStackTrace();
		    System.exit(-1);
		  }
	}
	public Socket getClientEnd() {
		return clientEnd;
	}
	public void setClientEnd(Socket clientEnd) {
		this.clientEnd = clientEnd;
	}
	public PrintWriter getClientOut() {
		return clientOut;
	}
	public void setClientOut(PrintWriter clientOut) {
		this.clientOut = clientOut;
	}
	public BufferedReader getClientIn() {
		return clientIn;
	}
	public void setClientIn(BufferedReader clientIn) {
		this.clientIn = clientIn;
	}
	public void parseLisp(String input) {
		try {
			clientOut.println(input);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
