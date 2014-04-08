package simulator;

public class SimulatorDLO implements ISimulatorDLO {
	private double brutto;
	private double tara;
	private String displayText;
	
	public SimulatorDLO() {
		this.brutto = 0;
		this.tara = 0;
		this.displayText = "";
		//Tjezt
	}
	
	@Override
	public double getBrutto() {
		return brutto;
	}
	
	@Override
	public double getTara() {
		return tara;
	}
	
	@Override
	public String getDisplayText() {
		return displayText;
	}
	
	@Override
	public void setBrutto(double brutto) {
		this.brutto = brutto;
	}

	@Override
	public void setTara() {
		this.tara = this.brutto;
	}
	
	@Override
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
}
