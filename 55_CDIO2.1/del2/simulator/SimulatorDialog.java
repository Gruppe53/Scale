package simulator;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.*;

import net.miginfocom.swing.MigLayout;

public class SimulatorDialog extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private JPanel simPanel = new JPanel(new MigLayout());
	private JTextArea simTxt = new JTextArea();
	private JScrollPane simScr = new JScrollPane(simTxt);
	
	private ISimulatorDAO sim;
	
	private ServerSocket srvSocket;
	private Socket cltSocket;
	private DataInputStream in;
	private DataOutputStream out;
	private String port;
	
	public SimulatorDialog(ISimulatorDAO sim, String port) {
		this.sim = sim;
		this.port = port;
		
		setLayout(new MigLayout());
		
		simPanel.setBackground(Color.decode("#161616"));
		simPanel.getLayout();
		
		simTxt.setBorder(null);
		simTxt.setBackground(simPanel.getBackground());
		simTxt.setForeground(Color.decode("#38ce49"));
		simTxt.setCaretColor(Color.decode("#38ce49"));
		simTxt.setPreferredSize(new Dimension(400,450));
		simTxt.setEditable(true);
		simTxt.setFont(new Font("Courier New", Font.BOLD, 11));
		
		simTxt.setText("> Server started and is listening on port " + port + "...\n"
					+ ">\n"
					+ "> **********Mettler Simulator**********\n"
					+ ">\n"
					+ "> Client: \n" // getCltAdress()
					+ ">\n"
					+ "> Netto: " + sim.getNetto() + "\n"
					+ "> Display text: " + sim.getDisplayText() + "\n"
					+ ">\n"
					+ "> Available commands:\n"
					+ "> B, set brutto.\n"
					+ "> S, send current stable weight.\n"
					+ "> T, tare scale.\n"
					+ "> D, set display text.\n"
					+ "> DW, print current scale status.\n"
					+ "> Q, quit.\n"
					+ "> **********Mettler Simulator**********\n"
					+ ">\n");
		
		((AbstractDocument)simTxt.getDocument()).setDocumentFilter(new PromptTextFilter());
		
		simScr.setPreferredSize(new Dimension(400,450));
		simScr.setBorder(null);
		
		simPanel.add(simScr);
		
		add(simPanel);
	}
	
	private String getCltAdress() {
		if(cltSocket.isConnected())
			return cltSocket.getInetAddress().getHostAddress();
		
		return "No connection established.";
	}

	private void startSrv() {
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
