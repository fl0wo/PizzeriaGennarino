package main.Serber;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutionException;

import main.messages.MSG;

/**
 *
 * @author saban
 */
public class TCPSerberTask extends SerberTask {

    private ServerSocket ss;
    private HandlerManager handler;

    public TCPSerberTask(ServerSocket ss) throws IOException {
        this.ss = ss;
        try {
            handler = new HandlerManager(2);
        } catch (InterruptedException | ExecutionException ex) {
        }
    }

    @Override
    public void run() {

        System.out.println("! SERBER STARTED RUNNING !");

        while (isRunning()) {
            try {
                handler.addSocket(ss.accept());
            } catch (IOException ex) {
                System.err.println(MSG.imp_serber_avv);
            }
        }

        //finisce il serber
    }

}
