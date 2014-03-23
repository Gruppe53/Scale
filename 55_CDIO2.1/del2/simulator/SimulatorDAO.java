package simulator;

public class SimulatorDAO implements ISimulatorDAO {
	private ISimulatorDLO simDlo;

	public SimulatorDAO(ISimulatorDLO simDlo) {
		this.simDlo = simDlo;
	}
	
	public int getNetto() {
		return (simDlo.getBrutto() - simDlo.getTara());
	}

	public String getDisplayText() {
		return simDlo.getDisplayText();
	}

	@Override
	public String getResponse(String input) {
		return simDlo.getResponse(input);
	}
}
