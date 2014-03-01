package scale;

import java.io.*;
import java.util.*;

public class OperatorDTO implements IOperatorDTO {
	ArrayList<Operator> oprs = new ArrayList<Operator>();
	
	private class Operator {
		int id;
		String name;
		String cpr;
		String password;
		
		public Operator(String name, String cpr, String password) {
			super();
			this.id = (oprs.size() + 1);
			this.name = name;
			this.cpr = cpr;
			this.password = password;
		}
	}
	
	public OperatorDTO() {
		createUser("God", "000000-0000", "roflskates");
		
		this.createOprList();
	}

	private void createOprList() {
		try {
			FileInputStream oprList = new FileInputStream("materials/opr.list");
			ObjectInputStream read = new ObjectInputStream(oprList);
			
			do {
				oprs.add((Operator) read.readObject());
			}
			while(read != null);
			
			read.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Operator> getOprList() {
		return oprs;
	}

	public void createUser(String name, String cpr, String password) {
		try {
			FileOutputStream oprs = new FileOutputStream("materials/opr.list");
			ObjectOutputStream save = new ObjectOutputStream(oprs);
			
			save.writeObject(new Operator(name, cpr, password));
			
			save.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		this.createOprList();
	}
	
	public String getName(int id) {
		for (int i = 0; i < oprs.size(); i++)
			if(oprs.get(i).id == id)
				return oprs.get(i).name;
		return null;
	}
	
	public String getCpr(int id) {
		for (int i = 0; i < oprs.size(); i++)
			if(oprs.get(i).id == id)
				return oprs.get(i).cpr;
		return null;
	}
	
	public String getPassword(int id) {
		for (int i = 0; i < oprs.size(); i++)
			if(oprs.get(i).id == id)
				return oprs.get(i).password;
		return null;
	}
	
	public void setPassword(int id, String password) {
		for (int i = 0; i < oprs.size(); i++)
			if(oprs.get(i).id == id)
				oprs.get(i).password = password;
	}
}
