package scale;

import java.util.ArrayList;

public interface IOperatorDTO {
	public boolean createOperator(String name, String cpr, char[] password);
	ArrayList<String> getOprList();
	String getName(String cpr);
	String getCpr(int oId);
	int getOprId(String cpr);
	String getPassword(String cpr);
	void setPassword(String cpr, String password);
	boolean deleteOperator(int id);
}