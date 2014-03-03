package users;

import java.util.*;

public class OperatorDAO implements IOperatorDAO {
	private IOperatorDTO opr;
	private String active;

	public OperatorDAO(IOperatorDTO opr) {
		this.opr = opr;
	}

	@Override
	public String getOperator(String cpr) throws DALException {
		return opr.getName(cpr) + ", CPR: " + opr.getCpr(opr.getOprId(cpr)) + ", ID: " + opr.getOprId(cpr);
	}

	@Override
	public ArrayList<String> getOperatorList() throws DALException {
		if(opr.getOprList() != null)
			return opr.getOprList();
		
		return null;
	}

	@Override
	public boolean createOperator(String name, String cpr, char[] password) throws DALException {
		if(opr.createOperator(name, cpr, password))
			return true;
		
		return false;
	}

	@Override
	public boolean updateOperator(char[] cPass, char[] nPass) throws DALException {
		if(Arrays.equals(cPass, opr.getPassword(active).toCharArray())) {
			opr.setPassword(active, new String(nPass));
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean deleteOperator(int id) {
		return opr.deleteOperator(id);
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

	@Override
	public boolean getActive() {
		if(active != null)
			return true;
		
		return false;
	}

	@Override
	public void userLogout() {
		active = null;
	}
}