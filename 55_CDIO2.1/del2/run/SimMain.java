package run;

import java.io.IOException;

import simulator.*;

public class SimMain {
	private static final String defaultPort = "8000"; // SETS DEFAULT LISTENING PORT FOR SERVER
	private static String port;
	
	private static ISimulatorDAO simDao;
	private static ISimulatorDLO simDlo;
	private static Thread simSrv;
	
	public SimMain() {
		if(port.isEmpty())
			port = defaultPort;
		
		try {
			simDlo = new SimulatorDLO();
			simDao = new SimulatorDAO(simDlo);
			simSrv = new SimServer(simDao, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		simSrv.start();
	}

    public static void main(String[] args) {
    	if(args.length > 0) {
	    	for(int i = 0; i < args.length; i++) {
	    		if(args[i].equals("-port"))
	    			port = args[i + 1];
	    	}
    	}
    	else {
    		port = "";
    	}
    	
    	if(port.isEmpty())
			port = defaultPort;
		
		try {
			simDlo = new SimulatorDLO();
			simDao = new SimulatorDAO(simDlo);
			simSrv = new SimServer(simDao, port);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		simSrv.start();
    }
}
