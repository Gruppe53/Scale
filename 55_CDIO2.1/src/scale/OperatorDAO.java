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
	public boolean createOperator(String name, String cpr, char[] password) throws DALException {
		if(opr.createOperator(name, cpr, password))
			return true;
		
		return false;
	}

	@Override
	public boolean updateOperator(char[] password) throws DALException {
		if(Arrays.equals(password, opr.getPassword(active).toCharArray())) {
			opr.setPassword(active, new String(password));
			
			return true;
		}
		
		return false;
	}
	
	public boolean deleteOperator(String cpr) {
		if(opr.getOprId(cpr) != -1)
			if(opr.deleteOperator(cpr))
				return true;
		
		return false;
	}
	
	@Override
	public String getInitials(String cpr) {
		String ini = "";
		
		for(String s : opr.getName(cpr).split(" "))
			ini += s.substring(0,1).toLowerCase();
		
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