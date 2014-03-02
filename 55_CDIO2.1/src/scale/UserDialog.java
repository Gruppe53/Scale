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
	private JTextField cprFirst;
	private JTextField cprSecond;
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
		
		userName = new JLabel("CPR");
		userPass = new JLabel("Password");
		cprFirst = new JTextField(4);
		cprSecond = new JTextField(3);
		pass = new JPasswordField(10);
		
		JLabel cprSep = new JLabel("-");
		cprSep.setPreferredSize(new Dimension(5,18));
		
		userLogin = new JButton("Login");
		
		JSeparator y = new JSeparator(SwingConstants.VERTICAL);
		y.setPreferredSize(new Dimension(1,60));
		y.setForeground(Color.decode("#d5dfe5"));
		
		ctrlPanel.add(userName);
		ctrlPanel.add(cprFirst);
		ctrlPanel.add(cprSep);
		ctrlPanel.add(cprSecond);
		ctrlPanel.add(y, "span 1 2");
		ctrlPanel.add(userLogin, "wrap");
		ctrlPanel.add(userPass);
		ctrlPanel.add(pass, "span 1 6");
		
		userPanel.add(ctrlPanel);
		
		add(userPanel);
		
		userLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userLogin();
            }
        });
	}

	private void userLogin() {
		String cprNr = cprFirst.getText() + "-" + cprSecond.getText();
		
		if(opr.tryLogin(cprNr, pass.getPassword()))
			tab.setEnabledAt(1, true);
	}
}
