package simulator;

public class SimulaturDAO implements ISimulatorDAO {
	private ISimulatorConnection simCon;
	private ISimulatorDLO simDlo;

	public SimulaturDAO(ISimulatorConnection simCon, ISimulatorDLO simDlo) {
		this.simCon = simCon;
		this.simDlo = simDlo;
	}
}
