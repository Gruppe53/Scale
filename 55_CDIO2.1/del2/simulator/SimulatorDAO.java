package simulator;

public class SimulatorDAO implements ISimulatorDAO {
	private ISimulatorDLO sim;

	public SimulatorDAO(ISimulatorDLO sim) {
		this.sim = sim;
	}
	
	public String getNetto() {
		return String.valueOf((sim.getBrutto() - sim.getTara()));
	}

	public String getDisplayText() {
		return sim.getDisplayText();
	}

	@Override
	public void setDisplayText(String displayText) {
		sim.setDisplayText(displayText);
	}

	@Override
	public String getTara() {
		return String.valueOf(sim.getTara());
	}

	@Override
	public void setTara() {
		sim.setTara();
	}

	@Override
	public String getBrutto() {
		return String.valueOf(sim.getBrutto());
	}

	@Override
	public void setBrutto(double brutto) {
		sim.setBrutto(brutto);
	}
}
