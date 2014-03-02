package scale;

import java.util.*;

public class OperatorDTO implements IOperatorDTO {
	ArrayList<Operator> oprs = new ArrayList<Operator>();
	
	public class Operator {
		int id;
		String name;
		String cpr;
		String password;
		
		public Operator(int id, String name, String cpr, String password) {
			super();
			this.id = id;
			this.name = name;
			this.cpr = cpr;
			this.password = password;
		}
		
		public Operator(String name, String cpr, String password) {
			super();
			this.id = oprs.get(oprs.size() - 1).id + 1;
			this.name = name;
			this.cpr = cpr;
			this.password = password;
		}
	}
	
	public OperatorDTO() {
		oprs.add(new Operator(10, "Lars Peter Jensen", "123456-7890", "02324it!"));
		oprs.add(new Operator(11, "Leo Jiahua", "654123-7890", "02324it!"));
		oprs.add(new Operator(12, "Malte Magnussen", "123456-0987", "02324it!"));
		oprs.add(new Operator(1, "God", "1-1", "gud"));
	}
	
	public boolean createOperator(String name, String cpr, char[] password) {
		oprs.add(new Operator(name, cpr, new String(password)));
		
		if(getName(cpr) != null)
			return true;
		
		return false;
	}
	
	public ArrayList<Operator> getOprList() {
		return oprs;
	}
	
	public String getName(String cpr) {
		for (int i = 0; i < oprs.size(); i++)
			if(oprs.get(i).cpr.equals(cpr))
				return oprs.get(i).name;
		return null;
	}
	
	public String getCpr(String cpr) {
		for (int i = 0; i < oprs.size(); i++)
			if(oprs.get(i).cpr.equals(cpr))
				return oprs.get(i).cpr;
		return null;
	}
	
	public int getOprId(String cpr) {
		for (int i = 0; i < oprs.size(); i++)
			if(oprs.get(i).cpr.equals(cpr))
				return oprs.get(i).id;
		return -1;
	}
	
	public String getPassword(String cpr) {
		for (int i = 0; i < oprs.size(); i++)
			if(oprs.get(i).cpr.equals(cpr))
				return oprs.get(i).password;
		return null;
	}
	
	public void setPassword(String cpr, String password) {
		for (int i = 0; i < oprs.size(); i++)
			if(oprs.get(i).cpr.equals(cpr))
				oprs.get(i).password = password;
	}
	
	@Override
	public boolean deleteOperator(String cpr) {
		if(oprs.remove(getIndex(cpr)) != null)
			return true;
		
		return false;
	}
	
	private int getIndex(String cpr) {
		for(int i = 0; i < oprs.size(); i++)
			if(oprs.get(i).cpr.equals(cpr))
				return i;
		
		return -1;
	}
}
