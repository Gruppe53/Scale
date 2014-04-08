package scale;

public interface IScaleConnection {
	boolean scaleConnect(String host, String port);
	void scaleDisconnect();
	String getScaleResponse(String output);
	String getHost();
	String getPort();
}