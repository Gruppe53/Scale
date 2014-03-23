package simulator;

public class SimulatorDLO implements ISimulatorDLO {
	private int brutto;
	private int tara;
	private String displayText;
	
	public SimulatorDLO() {
		this.brutto = 0;
		this.tara = 0;
		this.displayText = "";
	}

	public int getBrutto() {
		return brutto;
	}

	public void setBrutto(int brutto) {
		this.brutto = brutto;
	}

	public int getTara() {
		return tara;
	}

	public void setTara(int tara) {
		this.tara = tara;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	
	public String getDisplayText() {
		return displayText;
	}

	public String getResponse(String input) {
		// TODO
		
		return null;
	}
}
