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
		
		cltSocket = srvSocket.accept();
		
		out = new PrintWriter(cltSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(cltSocket.getInputStream()));
	}
	
	public void run() {
		updateScreen();
		
		try{
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.startsWith("DN")) {
					// TODO
				}
				else if (inputLine.startsWith("D")) {
					if (inputLine.equals("D"))
						sim.setDisplayText("");
					else
						sim.setDisplayText(inputLine.substring(2,inputLine.length()));
					
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
					
					out.println("S " + (sim.getNetto())+ " kg " +"\r\n");
				}
				
				else if (inputLine.startsWith("B")) {
					String temp = inputLine.substring(2,inputLine.length());
					
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
		System.out.println("Netto: " + sim.getNetto()+ " kg" );
		System.out.println("Displaytext: " + sim.getDisplayText() );
		System.out.println("*************************************************");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Debug info:");
		System.out.println("Hooked up to " + cltSocket.getInetAddress() );
		System.out.println("Brutto: " + sim.getBrutto() + " kg" );
		System.out.println("Streng modtaget: " + inputLine);
		System.out.println(" ");
		System.out.println("Denne vegt simulator lytter på ordrene ");
		System.out.println("D, DN, S, T, B, Q ");
		System.out.println("paa kommunikationsporten. ");
		System.out.println("******");
		System.out.println("Tast T for tara (svarende til knaptryk paa vegt)");
		System.out.println("Tast B for ny brutto (svarende til at belastningen paa vegt ændres)");
		System.out.println("Tast Q for at afslutte program program");
		System.out.println("Indtast (T/B/Q for knaptryk / brutto ændring / quit)");
		System.out.println("> ");
	}
}
