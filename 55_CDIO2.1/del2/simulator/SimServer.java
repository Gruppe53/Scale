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

	public SimServer(String port) throws IOException {
		this.srvSocket = new ServerSocket(Integer.parseInt(port));
	}
	
	public void run() {
		while(true) {
			try {
				cltSocket = srvSocket.accept();
				
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
