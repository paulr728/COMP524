package main.lisp.distributed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import main.lisp.interpreter.InterpreterModel;
import main.util.parallel.SystemOutReplacingTeeOutputStream;
import main.util.parallel.TeeOutputStream;

public class ServerModel {
	private ServerSocket serverSocketFactory;
	private Socket serverEndOfCurrentClient;
	private static TeeOutputStream teeStream;
	private InterpreterModel interpreter;

	private String host;
	private int port;

	public ServerModel(String hostName, int portNumber) {
		host = hostName;
		port = portNumber;
		teeStream = new SystemOutReplacingTeeOutputStream();
	}

	public InterpreterModel getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(InterpreterModel interpreter) {
		this.interpreter = interpreter;
	}

	public void startServe() {
		try {
			// 1. serve itself
			Thread t = new Thread(() -> {
				// interpret its own input
				try (Scanner keyboard = new Scanner(System.in)) {
					String line = keyboard.nextLine();
					while (!line.equals(".")) {
						interpreter.newInput(line);

						line = keyboard.nextLine();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			t.start();
			// serve clients
			serverSocketFactory = new ServerSocket(port);
			while (true) {
				serveNextClient();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void serveNextClient() {
		try {
			serverEndOfCurrentClient = serverSocketFactory.accept();
			//System.out.println("Client connected:" + serverEndOfCurrentClient);
			
			Thread t = new Thread(new ServeClientRunnable(serverEndOfCurrentClient, getInterpreter(), teeStream));
			t.setName(serverEndOfCurrentClient.toString());
			t.start();

		} catch (Exception e) {
			if (e.getMessage().equals("Connection reset")) {
				System.out.println("Client terminated");
			}
		}
	}

}
