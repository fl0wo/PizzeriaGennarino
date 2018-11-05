package main.Serber;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author saban
 */
public class TCPSerber {
    
    private ServerSocket ss;
    private SerberTask serberTask;
    
    public TCPSerber(int port) throws IOException{
        ss = new ServerSocket(port);
        serberTask = new TCPSerberTask(ss);
    }

    public void start() {
        serberTask.__run();
    }
    
}
