package users;

import java.util.ArrayList;

public interface IOperatorDAO {
	String getOperator(String cpr) throws DALException;
	ArrayList<String> getOperatorList() throws DALException;
	boolean createOperator(String name, String cpr, char[] password) throws DALException;
	boolean updateOperator(char[] cPass, char[] nPass) throws DALException;
	boolean deleteOperator(int id);
	boolean tryLogin(String cprNr, char[] password);
	String getInitials(String cpr);
	boolean getActive();
	void userLogout();
}
