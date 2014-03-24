package simulator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimServer extends Thread {
	private ServerSocket srvSocket;
	private Socket cltSocket;
	private DataInputStream in;
	private DataOutputStream out;
	
	private ISimulatorDAO sim;
	private String inline;

	public SimServer(ISimulatorDAO sim, String port) throws IOException {
		this.sim = sim;
		this.srvSocket = new ServerSocket(Integer.parseInt(port));
		
		cltSocket = srvSocket.accept();
		
		in = new DataInputStream(cltSocket.getInputStream());
		out = new DataOutputStream(cltSocket.getOutputStream());
	}
	
	public void run() {
		try{ 
			while (!(inline = in.readLine().toUpperCase()).isEmpty()) {
				if (inline.startsWith("DN")) {
					// TODO
				}
				else if (inline.startsWith("D")) {
					if (inline.equals("D"))
						sim.setDisplayText("");
					else
						sim.setDisplayText(inline.substring(2,inline.length()));
					
					updateScreen(); 
					 
					out.writeBytes("DB"+"\r\n");
				}
				
				else if (inline.startsWith("T")) {
					out.writeBytes("T " + (tara) + " kg "+"\r\n");
					
					sim.setTara();
					
					updateScreen();
				}
				
				else if (inline.startsWith("S")) {
					updateScreen();
					
					out.writeBytes("S " + (sim.getNetto())+ " kg " +"\r\n");
				}
				
				else if (inline.startsWith("B")) {
					String temp = inline.substring(2,inline.length());
					
					sim.setBrutto(Double.parseDouble(temp));
					
					updateScreen(); 
					
					out.writeBytes("DB"+"\r\n");
				}
				
				else if ((inline.startsWith("Q"))) {
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
		// TODO Auto-generated method stub
		
	}
}
