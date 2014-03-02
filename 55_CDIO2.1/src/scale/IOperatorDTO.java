package scale;

import java.util.ArrayList;

import scale.OperatorDTO.Operator;

public interface IOperatorDTO {
	public boolean createOperator(String name, String cpr, char[] password);
	ArrayList<Operator> getOprList();
	String getName(String cpr);
	String getCpr(String cpr);
	int getOprId(String cpr);
	String getPassword(String cpr);
	void setPassword(String cpr, String password);
	boolean deleteOperator(String cpr);
}