package simulator;

public class SimulaturDAO implements ISimulatorDAO {
	private ISimulatorDLO simDlo;

	public SimulaturDAO(ISimulatorDLO simDlo) {
		this.simDlo = simDlo;
	}
	
	public int getNetto() {
		return (simDlo.getBrutto() - simDlo.getTara());
	}

	public String getDisplayText() {
		return simDlo.getDisplayText();
	}
}
