package scale;

import java.util.ArrayList;

public interface IOperatorDAO {
	OperatorDTO getOperatoer(int oprId) throws DALException;
	ArrayList<OperatorDTO> getOperatorList() throws DALException;
	void createOperatoer(OperatorDTO opr) throws DALException;
	void updateOperatoer(OperatorDTO opr) throws DALException;
	String getInitials(OperatorDTO opr);
}
