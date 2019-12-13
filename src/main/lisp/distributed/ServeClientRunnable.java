package main.lisp.distributed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import main.lisp.interpreter.InterpreterModel;
import main.util.parallel.TeeOutputStream;

public class ServeClientRunnable implements Runnable {

	private Socket serverEndOfCurrentClient;
	private InterpreterModel interpreter;

	private TeeOutputStream teeStream;

	public ServeClientRunnable(Socket _serverEndOfCurrentClient, InterpreterModel interp, TeeOutputStream _teeStream) {
		serverEndOfCurrentClient = _serverEndOfCurrentClient;
		interpreter = interp;
		teeStream = _teeStream;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			PrintWriter serverOut = new PrintWriter(serverEndOfCurrentClient.getOutputStream(), true);
			BufferedReader serverIn = new BufferedReader(
					new InputStreamReader(serverEndOfCurrentClient.getInputStream()));
			teeStream.addStream(serverEndOfCurrentClient.getOutputStream());
			while(true) {
				processNextEntry(serverIn.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

	protected void processNextEntry(String input) throws IOException {
		if (interpreter == null) {
			System.out.println("Null interpreter!");
			return;
		}
		//System.out.println("Received:" + input);
		interpreter.newInput(input);
	}

}
