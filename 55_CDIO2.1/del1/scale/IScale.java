package scale;

public interface IScale {
	String scaleConnect(String host, String port);
	String scaleDisconnect();
	String scaleRead();
	String scaleTare();
	String scaleZero();
	String scaleMessage(String msg);
	String scaleDisplay();
	String getConnection();
}
