package simulator;

public interface ISimulatorDLO {
	int getBrutto();
	int getTara();
	void setBrutto(int brutto);
	void setTara(int tara);
	String getDisplayText();
	void setDisplayText(String displayText);
	String getResponse(String input);
}
