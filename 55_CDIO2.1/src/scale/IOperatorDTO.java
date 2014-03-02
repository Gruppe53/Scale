package scale;

import java.util.ArrayList;

import scale.OperatorDTO.Operator;

public interface IOperatorDTO {
	ArrayList<Operator> getOprList();
	String getName(String cpr);
	String getCpr(String cpr);
	String getPassword(String cpr);
	void setPassword(String cpr, String password);
}