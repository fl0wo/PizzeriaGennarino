package main.Serber.Manager;

import main.Serber.Manager.user.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import main.messages.EXIT_CODE;
import main.messages.MSG;

/**
 *
 * @author saban
 */
public class ManagerRangeClient extends ManagerClient<String> {

    private int rangeMin, rangeMax;
    private final int FRAME_RATE = 60;

    public ManagerRangeClient(int rangeMin, int rangeMax, ArrayList<Cliente> sc) {
        super(sc);
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        
    }

    @Override
    public String call() {
        while (true) {

            dormi(1000 / FRAME_RATE);

            for (int i = rangeMin; i < rangeMax; i++) {// controlla i socket che sono connessi

                BufferedReader bf = getBuff(sc.get(i).getSocket());

                try {
                    // If he print to me
                    if (bf.ready()) {
                        String t = bf.readLine();
                        reagisci(t, i);
                    } else {
                        /*
                        try {
                            //if he quitted
                            sc.get(i).getSocket().getInputStream().read();
                            //isDisconnected(sc.get(i).getSocket());
                        } catch (IOException ex) {
                            //rage quitted to be sure 100%
                            sc.remove(i);
                            //System.out.println("REMOVED : " + rimuovi(i).getNomeCliente() + " DISCONNECTED");
                        }
                        */
                    }
                } catch (IOException ex) {
                    System.err.println(MSG.errore_generico);
                }

            }
        }
    }

    /**
     * adatta
     */
    public void setStart(int start) {
        this.rangeMin = start;
    }

    public void setEnd(int end) {
        this.rangeMax = end;
    }

    private void reagisci(String readLine, int posMioSocket) throws IOException {
        //lock syncronized
        
            super.setInteressato(posMioSocket);
            
            EXIT_CODE t = super.__execute(readLine);
            System.out.println(MSG.exit_code_msgs[t.getNumVal()]);
        
        
        /*
        String messaggioDalClient = readLine;
        System.out.println("[SOCKET " + posMioSocket + "]" + " : " + messaggioDalClient + " (dal thread : " + Thread.currentThread().getName() + ")");
        for (int posAltroSocket = 0; posAltroSocket < sc.size(); posAltroSocket++) {
            if(posAltroSocket!=posMioSocket){
                PrintWriter pw = new PrintWriter(sc.get(posAltroSocket).getSocket().getOutputStream(),true);
                pw.println("["+sc.get(posMioSocket).getNomeCliente()+"] : "+readLine);
            }
        }
        
         */
    }
   

}
