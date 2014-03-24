package simulator;

public class SimulatorDAO implements ISimulatorDAO {
	private ISimulatorDLO sim;

	public SimulatorDAO(ISimulatorDLO sim) {
		this.sim = sim;
	}
	
	public int getNetto() {
		return (sim.getBrutto() - sim.getTara());
	}

	public String getDisplayText() {
		return sim.getDisplayText();
	}

	@Override
	public void setDisplayText(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTara() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTara() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getBrutto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBrutto(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
}
