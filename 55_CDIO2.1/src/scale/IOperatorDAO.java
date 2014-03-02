package scale;

import java.util.ArrayList;

import scale.OperatorDTO.Operator;

public interface IOperatorDAO {
	OperatorDTO getOperator(int oprId) throws DALException;
	ArrayList<Operator> getOperatorList() throws DALException;
	void createOperator(OperatorDTO opr) throws DALException;
	void updateOperator(OperatorDTO opr) throws DALException;
	String getInitials(OperatorDTO opr);
	boolean tryLogin(String cprNr, char[] password);
}
