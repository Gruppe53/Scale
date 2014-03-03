package users;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
	private JButton userLogout;
	
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
		pass = new JPasswordField();
		pass.setPreferredSize(new Dimension(98,20));
		
		JLabel cprSep = new JLabel("-");
		cprSep.setPreferredSize(new Dimension(5,18));
		
		userLogin = new JButton("Login");
		userLogout = new JButton("Logout");
		userLogout.setEnabled(false);
		
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
		ctrlPanel.add(pass, "span 3 1");
		ctrlPanel.add(userLogout, "wrap");
		
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
		textArea.append("[" + getDate() + "] Welcome...\n");
		
		userPanel.add(ctrlPanel, "wrap");
		userPanel.add(cmdPanel, "wrap");
		userPanel.add(txtPanel);
		
		add(userPanel);
		
		userLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	userLogin();
            }
        });
		
		userLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	userLogout();
            }
        });
		
		listUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listUsers();
            }
        });
		
		createUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createUser();
            }
        });
		
		deleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteUser(Integer.parseInt(JOptionPane.showInputDialog("Choose ID")));
            }
        });
		
		newPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newPassword();
            }
        });
	}

	private void userLogin() {
		String cprNr = cprFirst.getText() + "-" + cprSecond.getText();
		
		if(opr.tryLogin(cprNr, pass.getPassword())) {
			tab.setEnabledAt(1, true);
			
			userLogout.setEnabled(true);
			listUsers.setEnabled(true);
			createUser.setEnabled(true);
			deleteUser.setEnabled(true);
			newPassword.setEnabled(true);
			
			userLogin.setEnabled(false);
			cprFirst.setEnabled(false);
			cprSecond.setEnabled(false);
			pass.setEnabled(false);
			
			try {
				textArea.append("[" + getDate() + "] Logged in as " + opr.getOperator(cprNr) + "\n");
			}
			catch (DALException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void userLogout() {
		if(opr.getActive()) {
			tab.setEnabledAt(1, false);
			
			userLogout.setEnabled(false);
			listUsers.setEnabled(false);
			createUser.setEnabled(false);
			deleteUser.setEnabled(false);
			newPassword.setEnabled(false);
			
			userLogin.setEnabled(true);
			cprFirst.setEnabled(true);
			cprSecond.setEnabled(true);
			pass.setEnabled(true);
			
			opr.userLogout();
			
			textArea.append("[" + getDate() + "] Logged out.\n");
		}
	}
	
	private void listUsers() {
		try {
			ArrayList<String> str = opr.getOperatorList();
			
			textArea.append("[" + getDate() + "] List of users:\n");
			
			for(int i = 0; i < str.size(); i++)
				textArea.append("[" + getDate() + "] " + str.get(i) + "\n");
			
		}
		catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	private void createUser() {
		String name = JOptionPane.showInputDialog("Name");
		String cpr = JOptionPane.showInputDialog("CPR (ddmmyy-MRRG)");
		
		JPasswordField nPass = new JPasswordField();
		JPasswordField qPass = new JPasswordField();
		
		JOptionPane.showConfirmDialog(null, nPass, "Enter password", 0);
		JOptionPane.showConfirmDialog(null, qPass, "Enter password again", 0);

		if(Arrays.equals(nPass.getPassword(), qPass.getPassword())) {
			try {
				if(opr.createOperator(name, cpr, nPass.getPassword()))
					textArea.append("[" + getDate() + "] User with name: " + name + " has been added.\n");
			}
			catch (DALException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void deleteUser(int id) {
		if(JOptionPane.showConfirmDialog(null, "Are you sure?") == 0)
			if(opr.deleteOperator(id))
				textArea.append("[" + getDate() + "] Deleted user with id: " + id + "\n");
	}
	
	private void newPassword() {
		JPasswordField cPass = new JPasswordField();
		JPasswordField nPass = new JPasswordField();
		JPasswordField qPass = new JPasswordField();
		
		JOptionPane.showConfirmDialog(null, cPass, "Enter current password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showConfirmDialog(null, nPass, "Enter new password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showConfirmDialog(null, qPass, "Enter new password again", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if(Arrays.equals(nPass.getPassword(), qPass.getPassword())) {
			try {
				if(opr.updateOperator(cPass.getPassword(), nPass.getPassword())) {
					textArea.append("[" + getDate() + "] Password has successfully been changed.\n");
				}
			}
			catch (DALException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getDate() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
}
