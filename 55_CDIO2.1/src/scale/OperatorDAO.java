package scale;

import java.util.*;

import scale.OperatorDTO.Operator;

public class OperatorDAO implements IOperatorDAO {
	private IOperatorDTO opr;
	private String active;

	public OperatorDAO(IOperatorDTO opr) {
		this.opr = opr;
	}

	@Override
	public String getOperator(String cpr) throws DALException {
		return opr.getName(cpr) + ", CPR: " + opr.getCpr(cpr) + ", ID: " + opr.getOprId(cpr);
	}

	@Override
	public ArrayList<Operator> getOperatorList() throws DALException {
		return opr.getOprList();
	}

	@Override
	public void createOperator(OperatorDTO opr) throws DALException {
		opr.getName(active);
	}

	@Override
	public void updateOperator(OperatorDTO opr) throws DALException {
		opr.setPassword(active, "tjir");
	}
	
	@Override
	public String getInitials(OperatorDTO opr) {
		String ini = "";
		
		return ini;
	}

	@Override
	public boolean tryLogin(String cpr, char[] password) {
		if(Arrays.equals(opr.getPassword(cpr).toCharArray(), password)) {
			active = cpr;
			password = null;
			
			return true;
		}
			
		return false;
	}
}
