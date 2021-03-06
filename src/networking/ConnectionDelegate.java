package networking;

public interface ConnectionDelegate {

  public void startOnlineGame();

  public void connectionFailed(String message);

  public void readData(byte[] data);

  public byte[] onWrite();

  public boolean shouldRead();

  public void didWrite();

  public boolean shouldWrite();

  public void setLevel(String path, String backGround);
}
