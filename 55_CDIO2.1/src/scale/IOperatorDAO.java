package scale;

import java.util.ArrayList;

import scale.OperatorDTO.Operator;

public interface IOperatorDAO {
	String getOperator(String cpr) throws DALException;
	ArrayList<Operator> getOperatorList() throws DALException;
	boolean createOperator(String name, String cpr, char[] password) throws DALException;
	boolean updateOperator(char[] password) throws DALException;
	String getInitials(String cpr);
	boolean tryLogin(String cprNr, char[] password);
}
