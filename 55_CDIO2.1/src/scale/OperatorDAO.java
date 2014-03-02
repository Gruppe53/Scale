package scale;

import java.util.*;

import scale.OperatorDTO.Operator;

public class OperatorDAO implements IOperatorDAO {
	
	private IOperatorDTO opr;

	public OperatorDAO(IOperatorDTO opr) {
		this.opr = opr;
	}

	@Override
	public OperatorDTO getOperator(int oprId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Operator> getOperatorList() throws DALException {
		return opr.getOprList();
	}

	@Override
	public void createOperator(OperatorDTO opr) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOperator(OperatorDTO opr) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getInitials(OperatorDTO opr) {
		String ini = "";
		
//		for(String s : opr.name.split(" ")) {
//			ini += s.substring(0, 1);
//		}
		
		return ini;
	}

	@Override
	public boolean tryLogin(String cpr, char[] password) {
		if(Arrays.equals(opr.getPassword(cpr).toCharArray(), password))
			return true;
		return false;
	}
}
