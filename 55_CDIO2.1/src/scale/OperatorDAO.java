package scale;

import java.util.ArrayList;

public class OperatorDAO implements IOperatorDAO {
	
	private IOperatorDTO opr;

	public OperatorDAO(IOperatorDTO opr) {
		this.opr = opr;
	}

	@Override
	public OperatorDTO getOperatoer(int oprId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<OperatorDTO> getOperatorList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createOperatoer(OperatorDTO opr) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOperatoer(OperatorDTO opr) throws DALException {
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
}
