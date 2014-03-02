package scale;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class UserDialog extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private JPanel userPanel;
	private JPanel ctrlPanel;
	private JPanel cmdPanel;
	private JScrollPane txtPanel;
	
	private JLabel userName;
	private JLabel userPass;
	private JTextField cprFirst;
	private JTextField cprSecond;
	private JPasswordField pass;
	private JButton userLogin;
	
	private JButton listUsers;
	private JButton createUser;
	private JButton deleteUser;
	private JButton newPassword;

	private IOperatorDAO opr;
	
	private JTabbedPane tab;
	
	private JTextArea textArea = new JTextArea();;
	
	public UserDialog(IOperatorDAO opr, JTabbedPane tab) {
		this.opr = opr;
		this.tab = tab;
		
		setLayout(new MigLayout());
		
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
		
		cmdPanel = new JPanel(new MigLayout());
		cmdPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#d5dfe5")), "User commands"));
		cmdPanel.setBackground(Color.white);
		cmdPanel.setPreferredSize(new Dimension(400,60));
		
		listUsers = new JButton("List");
		listUsers.setEnabled(false);
		createUser = new JButton("Create");
		createUser.setEnabled(false);
		deleteUser = new JButton("Delete");
		deleteUser.setEnabled(false);
		newPassword = new JButton("New pass");
		newPassword.setEnabled(false);
		
		cmdPanel.add(listUsers);
		cmdPanel.add(createUser);
		cmdPanel.add(deleteUser);
		cmdPanel.add(newPassword);
		
		txtPanel = new JScrollPane(textArea);
		txtPanel.setBackground(Color.white);
		txtPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#d5dfe5")), "Console"));
		txtPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		txtPanel.setPreferredSize(new Dimension(400, 200));
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.append("[" + getDate() + "]\tWelcome...\n");
		
		userPanel.add(ctrlPanel, "wrap");
		userPanel.add(cmdPanel, "wrap");
		userPanel.add(txtPanel);
		
		add(userPanel);
		
		userLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					userLogin();
				}
                catch (DALException e1) {
					e1.printStackTrace();
				}
            }
        });
	}

	private void userLogin() throws DALException {
		String cprNr = cprFirst.getText() + "-" + cprSecond.getText();
		
		if(opr.tryLogin(cprNr, pass.getPassword())) {
			tab.setEnabledAt(1, true);
			
			createUser.setEnabled(true);
			deleteUser.setEnabled(true);
			newPassword.setEnabled(true);
			
			userLogin.setEnabled(false);
			cprFirst.setEnabled(false);
			cprSecond.setEnabled(false);
			pass.setEnabled(false);
			
			textArea.append("[" + getDate() + "]\tLogged in as " + opr.getOperator(cprNr) + "\n");
		}
	}
	
	private String getDate() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
}
