package scale;

import java.util.regex.*;

public class Scale implements IScale {
	private IScaleConnection con;

	public Scale(IScaleConnection con) {
		this.con = con;
	}
	
	// TODO Correct responses
	// Responses should be checked, e.g. response of "S" can be either "S_S_[WEIGHT]_[UNIT]" or "S_I",
	// where the first response is acknowledge from server and a response with current stable weight,
	// while the second response also is acknowledge acknowledge from server that it indeed
	// did receive the message but wasn't able to execute it.
	// Hence, in this example, either return a formatted string with executed server response or
	// return an error string response (to the whichever class calling e.g. scaleRead - in this case
	// Dialog.class which then prints returned value (string) to the console window.)
	
	@Override
	public String scaleConnect(String host, String port) {
		if(con.scaleConnect(host, port))
			return getConnection();
		else
			return "Could not connect";
	}
	
	@Override
	public String scaleDisconnect() {
		con.scaleDisconnect();
		
		return getConnection();
	}

	@Override
	public String scaleRead() {
		String str = con.getScaleResponse("S");
		
		return "Current scale weight: " + getDigit(str) + " " + getUnit(str);
	}

	@Override
	public String scaleTare() {
		String str = con.getScaleResponse("T");
		
		return "Scale tared, tara reads: " + getDigit(str) + " " + getUnit(str);
	}

	@Override
	public String scaleZero() {
		con.getScaleResponse("Z");
		
		return "Scale has been zeroed.";
	}

	@Override
	public String scaleMessage(String msg) {
		con.getScaleResponse("D " + msg);
		
		return "Balance display set with \"" + msg + "\".";
	}

	@Override
	public String scaleDisplay() {
		con.getScaleResponse("DW");
		
		return "Weight successfully printed on scale display.";
	}
	
	@Override
	public String getConnection() {
		return con.getHost() + ":" + con.getPort();
	}
	
	private String getDigit(String str) {
		String res = "";
		
		Pattern p = Pattern.compile("-?[\\d+.\\d+]");
		Matcher m = p.matcher(str);
		
		while(m.find()) {
			res += m.group();
		}
		
		return res;
	}
	
	private String getUnit(String str) {
		return str.substring(str.length() - 2).trim();
	}
}
