package run;

import java.awt.*;

import javax.swing.*;

import scale.*;
import users.*;
import simulator.*;
import net.miginfocom.swing.MigLayout;

public class Main extends JComponent {
	private static final long serialVersionUID = 1L;
	private static JFrame scaleFrame;
	private static JComponent newContentPane;
	
	private JTabbedPane tab;
	
	private IScaleConnection con;
	private IScale scale;
	private ScaleDialog sDialog;

	private IOperatorDTO oprDTO;
	private IOperatorDAO oprDAO;
	private UserDialog  uDialog;
	
	public Main() {
		// Set layout manager
		setLayout(new MigLayout());
		
		// Create tabbed panel
		UIManager.put("TabbedPane.tabAreaBackground", Color.decode("#f0f0f0"));
		UIManager.put("TabbedPane.focus", Color.decode("#c8ddf2"));
		tab = new JTabbedPane();
		tab.setOpaque(true);
		
		// Instantiate objects
		con = new ScaleConnection();
		scale = new Scale(con);
		sDialog = new ScaleDialog(scale);
		
		oprDTO = new OperatorDTO();
		oprDAO = new OperatorDAO(oprDTO);
		uDialog = new UserDialog(oprDAO, tab);
		
		tab.addTab("Userpanel", uDialog);
		tab.addTab("Scale Console", sDialog);
		
		tab.setEnabledAt(1, false);
		
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
        newContentPane = new Main();
        newContentPane.setOpaque(true);
        scaleFrame.setContentPane(newContentPane);

        // Draw the window
        scaleFrame.setLocationRelativeTo(null);
        scaleFrame.pack();
        scaleFrame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI("Scale Console"); // Name of window
            }
        });
    }
}
