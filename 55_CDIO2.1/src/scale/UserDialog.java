package scale;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class UserDialog extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private JPanel userPanel;
	private JPanel ctrlPanel;
	
	private JLabel userName;
	private JLabel userPass;
	private JTextField name;
	private JPasswordField pass;
	private JButton userLogin;

	private IOperatorDAO opr;
	
	private JTabbedPane tab;
	
	public UserDialog(IOperatorDAO opr, JTabbedPane tab) {
		this.opr = opr;
		this.tab = tab;
		
		setLayout(new FlowLayout());
		
		userPanel = new JPanel(new MigLayout());
		userPanel.setBorder(BorderFactory.createBevelBorder(1, Color.decode("#ffffff"), Color.decode("#898c95"), Color.decode("#898c95"), Color.decode("#f0f0f0")));
		userPanel.setBackground(Color.WHITE);
		
		ctrlPanel = new JPanel(new MigLayout());
		ctrlPanel.setPreferredSize(new Dimension(400,1));
		ctrlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#d5dfe5")), "Login"));
		ctrlPanel.setBackground(Color.white);
		
		userName = new JLabel("Username");
		userPass = new JLabel("Password");
		name = new JTextField(10);
		pass = new JPasswordField(10);
		
		userLogin = new JButton("Login");
		
		JSeparator y = new JSeparator(SwingConstants.VERTICAL);
		y.setPreferredSize(new Dimension(1,60));
		y.setForeground(Color.decode("#d5dfe5"));
		
		ctrlPanel.add(userName);
		ctrlPanel.add(name);
		ctrlPanel.add(y, "span 1 2");
		ctrlPanel.add(userLogin, "wrap");
		ctrlPanel.add(userPass);
		ctrlPanel.add(pass);
		
		userPanel.add(ctrlPanel);
		
		add(userPanel);
		
		userLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userLogin();
            }
        });
	}

	private void userLogin() {
		tab.setEnabledAt(1, true);
	}
}
