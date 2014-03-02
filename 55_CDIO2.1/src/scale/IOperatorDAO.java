package scale;

import java.util.ArrayList;

import scale.OperatorDTO.Operator;

public interface IOperatorDAO {
	String getOperator(String cpr) throws DALException;
	ArrayList<Operator> getOperatorList() throws DALException;
	void createOperator(OperatorDTO opr) throws DALException;
	void updateOperator(OperatorDTO opr) throws DALException;
	String getInitials(OperatorDTO opr);
	boolean tryLogin(String cprNr, char[] password);
}
