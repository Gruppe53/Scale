package simulator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimServer {
	private ServerSocket srvSocket;
	private Socket cltSocket;
	private DataInputStream in;
	private DataOutputStream out;
	private String port;

	public SimServer() {
		try {
			this.srvSocket = new ServerSocket(Integer.parseInt(port));
		}
		catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			try {
				System.out.println("Waiting for client on port " + srvSocket.getLocalPort() + "...");
				
				cltSocket = srvSocket.accept();
				
				System.out.println("Just connected to " + cltSocket.getRemoteSocketAddress());
				
				in = new DataInputStream(cltSocket.getInputStream());
				out = new DataOutputStream(cltSocket.getOutputStream());
				
				out.writeUTF("Mettler Simulator v. something...");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
