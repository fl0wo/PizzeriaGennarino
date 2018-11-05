package main.Serber.Manager.comands;

import java.io.PrintWriter;
import main.messages.CMD;

/**
 *
 * @author Florian Sabani
 */
public class ConnectAndNotify extends Metodo {

    private static final int N_PAR = 0;

    public ConnectAndNotify() {
        super(N_PAR);
    }

    @Override
    public Boolean call() throws Exception {

        //Connect lo aggiunge in fine all'arraylist
        if(interessato>sc.size()-1)return false;

        sc.get(interessato).setHasConnected(true);
        
        //aggiorna me che sono appena entrato di quanta gente c'é in lobby
        PrintWriter toMe = new PrintWriter(sc.get(interessato).getSocket().getOutputStream(), true);
        PrintWriter toOther;
        
        
        //avviso tutti tranne me stesso ovviamente...
        for (int posOther = 0; posOther < sc.size(); posOther++) {
            if(posOther!=interessato){
                //aggiorna la gente che c'é in lobby che io sono entrato...
                toOther = new PrintWriter(sc.get(posOther).getSocket().getOutputStream(), true);

                // anche non ho il nome...

                toOther.println(connectedCMD(sc.get(interessato).getNomeCliente()));
                //anche se non ha il nome
                if(sc.get(posOther).isConnected())
                    toMe.println(connectedCMD(sc.get(posOther).getNomeCliente()));
            }
        }

        return true;

    }

    private String connectedCMD(String nomeCliente) {
        return CMD.INIT+""+CMD.CONNECT_NOTIFY+""+CMD.SEPARATOR+""+nomeCliente;
    }

}
