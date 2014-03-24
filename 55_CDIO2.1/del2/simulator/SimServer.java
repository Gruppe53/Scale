package simulator;

import java.io.*;
import java.net.*;

public class SimServer extends Thread {
	private ServerSocket srvSocket;
	private Socket cltSocket;
	private BufferedReader in;
	private PrintWriter out;
	
	private ISimulatorDAO sim;
	private String inputLine;

	public SimServer(ISimulatorDAO sim, String port) throws IOException {
		this.sim = sim;
		this.srvSocket = new ServerSocket(Integer.parseInt(port));
		
		System.out.println("Listening on port " + port + ", waiting for client...");
		
		cltSocket = srvSocket.accept();

		out = new PrintWriter(cltSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(cltSocket.getInputStream()));
	}
	
	public void run() {
		updateScreen();
		
		try {
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.startsWith("DN")) {
					// TODO
				}
				else if (inputLine.startsWith("D")) {
					if (inputLine.equals("D"))
						sim.setDisplayText("");
					else
						sim.setDisplayText(inputLine.substring(2, inputLine.length()));
					
					updateScreen(); 
					 
					out.println("DB"+"\r\n");
				}
				
				else if (inputLine.startsWith("T")) {
					out.println("T " + sim.getTara() + " kg "+"\r\n");
					
					sim.setTara();
					
					updateScreen();
				}
				
				else if (inputLine.startsWith("S")) {
					updateScreen();
					
					out.println("S " + sim.getNetto() + " kg " +"\r\n");
				}
				
				else if (inputLine.startsWith("B")) {
					String temp = inputLine.substring(2, inputLine.length());
					
					sim.setBrutto(Double.parseDouble(temp));
					
					updateScreen(); 
					
					out.println("DB"+"\r\n");
				}
				
				else if ((inputLine.startsWith("Q"))) {
					System.out.println("");
					System.out.println("Quitting...");
					
					System.in.close();
					System.out.close();
					
					in.close();
					out.close();
					
					System.exit(0);
				}
			}
		} 
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage()); 
		}
	}

	private void updateScreen() {
		System.out.println(" ");
		System.out.println("*************************************************");
		System.out.println("Netto: " + sim.getNetto() + " kg");
		System.out.println("Displaytext: " + sim.getDisplayText());
		System.out.println("*************************************************");
		System.out.println(" ");
		System.out.println("Client adress: " + cltSocket.getInetAddress().getHostAddress());
		System.out.println("Brutto: " + sim.getBrutto() + " kg");
		System.out.println("Read line: " + inputLine);
		System.out.println(" ");
		System.out.println("Implemented commands:");
		System.out.println("B, sets scale brutto.");
		System.out.println("S, display current stable weight.");
		System.out.println("T, tare scale.");
		System.out.println("D, insert new displaytext.");
		System.out.println("******");
		System.out.println("Press Q to exit simulator.");
		System.out.println("> ");
	}
}
