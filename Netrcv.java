import java.io.IOException;
import java.net.Socket;
 
public class Netrcv {
    private DirectoryTxr transmitter = null;
    Socket clientSocket = null;
    private boolean connectedStatus = false;
    private String ipAddress;
    String soureplacePath = null;
    String dstPath = "";
    public Netrcv() {
 
    }
 
    public void setIpAddress(String ip) {
        this.ipAddress = ip;
    }
 
    public void setsoureplacePath(String path) {
        this.soureplacePath = path;
    }
 
    public void setDstPath(String path) {
        this.dstPath = path;
    }
 
    private void createConnection() {
        Runnable connectRunnable = new Runnable() {
            public void run() {
                while (!connectedStatus) {
                    try {
                        clientSocket = new Socket(ipAddress, 3339);
                        connectedStatus = true;
                        transmitter = new DirectoryTxr(clientSocket, soureplacePath, dstPath);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
 
            }
        };
        Thread connectionThread = new Thread(connectRunnable);
        connectionThread.start();
    }
 
    public static void main(String[] args) {
        Netrcv main = new Netrcv();
        main.setIpAddress("localHost");
        main.setsoureplacePath("C:/Users/mypc/Desktop/GroupL/Sagar first");
        main.setDstPath("C:/Users/mypc/Desktop/GroupL/copied version");
        main.createConnection();
 
    }
}