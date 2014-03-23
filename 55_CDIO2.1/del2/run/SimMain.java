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
	
	private SimulatorDialog simDialog;
	private ISimulatorDAO simDao;
	private ISimulatorDLO simDlo;
	
	public SimMain() {
		// Set layout manager
		setLayout(new MigLayout());
		
		// Command-line Arguments
		if(port.isEmpty())
			port = defaultPort;
		
		System.out.println("Listening port: " + port);
		
		// Instantiate objects
		simDlo = new SimulatorDLO();
		simDao = new SimulaturDAO(simDlo);
		simDialog = new SimulatorDialog(simDao, port);
		
		add(simDialog);
	}
	
	private static void createAndShowGUI(String name) {
        // Create window
		scaleFrame = new JFrame(name);
		scaleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		scaleFrame.setBackground(Color.decode("#161616"));
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
    	if(args.length > 0) {
	    	for(int i = 0; i < args.length; i++)
	    		if(args[i].equals("-port"))
	    			port = args[i + 1];
    	}
    	else {
    		port = "";
    	}
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI("Scale Simulator");
            }
        });
    }
}
