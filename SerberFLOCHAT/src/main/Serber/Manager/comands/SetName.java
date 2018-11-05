package main.Serber.Manager.comands;

import java.io.PrintWriter;
import java.util.ArrayList;
import main.Serber.Manager.user.Cliente;
import main.messages.CMD;

/**
 *
 * @author saban
 */
public class SetName extends Metodo{

    private static final int N_PAR = 1;
    
    public SetName(ArrayList<Cliente> clienti, int interessato) {
        super(clienti, interessato,N_PAR);
    }

    public SetName() {
        super(N_PAR);
    }

    @Override
    public Boolean call() throws Exception {
        String oldName = sc.get(interessato).getNomeCliente();
        String newName = infos[0];
        
        sc.get(interessato).setNome(newName);

        PrintWriter toOther;

        int posMe = interessato;
        
        //avviso tutti tranne me stesso ovviamente...
        for (int posOther = 0; posOther < sc.size(); posOther++) {
            if(posOther!=posMe){
                if(sc.get(posOther).isConnected()){
                    //aggiorna la gente che c'é in lobby che ho cambiato nome
                    toOther = new PrintWriter(sc.get(posOther).getSocket().getOutputStream(), true);
                    toOther.println(setNameCMD(newName,oldName));
                }
            }
        }
        
        return true;
    }

    private String setNameCMD(String nomeCliente,String oldName) {
        return CMD.INIT+""+CMD.SET_NAME+""+CMD.SEPARATOR+""+oldName+""+CMD.SEPARATOR+""+nomeCliente;
    }
    
}
