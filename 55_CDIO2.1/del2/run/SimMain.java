package run;

import java.awt.*;

import javax.swing.*;

import simulator.*;

import net.miginfocom.swing.MigLayout;

public class SimMain extends JComponent {
	private static final long serialVersionUID = 1L;
	private static JFrame scaleFrame;
	private static JComponent newContentPane;
	private static final String defaultPort = "8000"; // SETS DEFAULT LISTENING PORT FOR SERVER
	private static String port;
	
	private JTabbedPane tab;
	
	private SimulatorDialog simDialog;
	private ISimulatorDAO simDao;
	private ISimulatorDLO simDlo;
	private ISimulatorConnection simCon;
	
	public SimMain() {
		// Set layout manager
		setLayout(new MigLayout());
		
		// Create tabbed panel
		UIManager.put("TabbedPane.tabAreaBackground", Color.decode("#f0f0f0"));
		UIManager.put("TabbedPane.focus", Color.decode("#c8ddf2"));
		tab = new JTabbedPane();
		tab.setOpaque(true);
		
		// Command-line Arguments
		if(port.isEmpty())
			port = defaultPort;
		
		System.out.println(port);
		
		// Instantiate objects
		simCon = new SimulatorConnection(port);
		simDlo = new SimulatorDLO();
		simDao = new SimulaturDAO(simCon, simDlo);
		simDialog = new SimulatorDialog(simDao);
		
		tab.addTab("Simulator", simDialog);
		
		add(tab);
	}
	
	private static void createAndShowGUI(String name) {
        // Create window
		scaleFrame = new JFrame(name);
		scaleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		scaleFrame.setBackground(Color.decode("#f0f0f0"));
		scaleFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("materials/icon.png"));
		scaleFrame.setResizable(false);

        // Create the content pane
        newContentPane = new SimMain();
        newContentPane.setOpaque(true);
        scaleFrame.setContentPane(newContentPane);

        // Draw the window
        scaleFrame.setLocationRelativeTo(null);
        scaleFrame.pack();
        scaleFrame.setVisible(true);
    }

    public static void main(String[] args) {
    	// Get command-line arguments
    	for(int i = 0; i < args.length; i++) {
    		if(args[i].equals("-port"))
    			port = args[i+1];
    	}
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI("Scale Simulator");
            }
        });
    }
}