package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import main.lisp.distributed.ClientModel;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String aHostName = args.length > 0? args[0]:"localhost";
		int portNumber = args.length > 1? Integer.parseInt(args[1]):9001;
//		String aHostName = "localhost";
//		int portNumber = 9001;
		
		try {
			ClientModel client = new ClientModel(aHostName, portNumber);
			new Thread(()->{
				// read client in 
				BufferedReader clientIn = client.getClientIn();
				while(true) {
					String feedback;
					try {
						feedback = clientIn.readLine();
						while(!feedback.isEmpty()) {
							System.out.println(feedback);
							feedback = clientIn.readLine();
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}).start();
			try (Scanner keyboard = new Scanner(System.in)){ 
				String line = keyboard.nextLine();
				while (!line.equals(".")) {
					client.parseLisp(line);
					
					line = keyboard.nextLine();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
