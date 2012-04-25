package Networking;

public interface ConnectionDelegate {
	public void startGame();
	public void connectionFailed(String message);
	public void readData(byte[] data);
	public byte[] onWrite();
	public boolean shouldRead();
	public boolean shouldWrite();
		
	
		
	
}