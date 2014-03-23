package run;

import simulator.*;

public class SimMain {
	private static final String defaultPort = "8000"; // SETS DEFAULT LISTENING PORT FOR SERVER
	private static String port;
	
	private ISimulatorDAO simDao;
	private ISimulatorDLO simDlo;
	
	public SimMain() {
		// Command-line Arguments
		if(port.isEmpty())
			port = defaultPort;
		
		simDlo = new SimulatorDLO();
		simDao = new SimulatorDAO(simDlo);
	}

    public static void main(String[] args) {
    	// Get command-line arguments
    	if(args.length > 0) {
	    	for(int i = 0; i < args.length; i++) {
	    		if(args[i].equals("-port"))
	    			port = args[i + 1];
	    	}
    	}
    	else {
    		port = "";
    	}
    }
}
