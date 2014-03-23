package simulator;

public class SimulatorDLO implements ISimulatorDLO {
	private int brutto;
	private int tara;
	
	public SimulatorDLO() {
		this.brutto = 0;
		this.tara = 0;
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
}
