package simulator;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.text.*;

import net.miginfocom.swing.MigLayout;

public class SimulatorDialog extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private JPanel simPanel = new JPanel(new MigLayout());
	private JTextArea simTxt = new JTextArea();
	private JScrollPane simScr = new JScrollPane(simTxt);
	
	private ISimulatorDAO sim;
	
	private SimServer srv;
	
	public SimulatorDialog(ISimulatorDAO sim, String port) {
		this.sim = sim;
		
		setLayout(new MigLayout());
		
		simPanel.setBackground(Color.decode("#161616"));
		
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
}
