package simulator;

import java.io.*;
import java.net.*;

public class SimulatorConnection extends Thread implements ISimulatorConnection {
	private ServerSocket srvSocket;
	private Socket cltSocket;
	
	public SimulatorConnection(String port) {
		try {
			this.srvSocket = new ServerSocket(Integer.parseInt(port));
			this.srvSocket.setSoTimeout(12000);
		}
		catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println("Waiting for client on port " +
				srvSocket.getLocalPort() + "...");
				
				cltSocket = srvSocket.accept();
				
				System.out.println("Just connected to "
				+ cltSocket.getRemoteSocketAddress());
				
				DataInputStream in = new DataInputStream(cltSocket.getInputStream());
				
				System.out.println(in.readUTF());
				
				DataOutputStream out = new DataOutputStream(cltSocket.getOutputStream());
				
				out.writeUTF("Thank you for connecting to " + cltSocket.getLocalSocketAddress() + "\nGoodbye!");
			}
			catch(SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			}
			catch(IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	public boolean disconnect() {
		try {
			cltSocket.close();
			
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
