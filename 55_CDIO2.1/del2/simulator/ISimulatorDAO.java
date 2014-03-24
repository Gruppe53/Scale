package simulator;

public interface ISimulatorDAO {
	int getNetto();
	String getDisplayText();
	void setDisplayText(String string);
	String getTara();
	void setTara();
	String getBrutto();
	void setBrutto(double parseDouble);
}
