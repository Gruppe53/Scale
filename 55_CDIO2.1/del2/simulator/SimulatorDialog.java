package simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class SimulatorDialog extends JComponent {
	private JPanel simPanel = new JPanel(new MigLayout());
	private JTextArea textArea = new JTextArea();
	private JScrollPane txtPanel = new JScrollPane(textArea);
	
	private ISimulatorDAO sim;
	
	public SimulatorDialog(ISimulatorDAO sim) {
		this.sim = sim;
		
		setLayout(new MigLayout());
		
		simPanel.setBorder(BorderFactory.createBevelBorder(1, Color.decode("#ffffff"), Color.decode("#898c95"), Color.decode("#898c95"), Color.decode("#f0f0f0")));
		simPanel.setBackground(Color.WHITE);
		
		txtPanel.setBackground(Color.white);
		txtPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#d5dfe5")), "Console"));
		txtPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		txtPanel.setPreferredSize(new Dimension(400, 200));
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.append("[" + getDate() + "]\tPress start to run Scale Simulator.\n");
		
		simPanel.add(txtPanel);
		
		add(simPanel);
	}
	
	private String getDate() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
}
