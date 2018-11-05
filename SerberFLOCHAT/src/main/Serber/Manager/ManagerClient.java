package main.Serber.Manager;

import main.Serber.Manager.comands.AddToLobby;
import main.Serber.Manager.comands.Metodo;
import main.Serber.Manager.comands.SetName;
import main.Serber.Manager.user.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Serber.Manager.comands.ConnectAndNotify;
import main.messages.CMD;
import main.messages.EXIT_CODE;
import static main.messages.EXIT_CODE.*;
import main.messages.MSG;

/**
 *
 * @author saban
 */
public abstract class ManagerClient<T> implements Callable<T>  {
    //future implementations...
 
    private int posInteressato;
    protected ArrayList<Cliente> sc;
    private HashMap<String,Metodo> map;
    
    protected ManagerClient(ArrayList<Cliente> sc){
        this.sc = sc;
        map = initAllMethods();
    }

    protected Cliente rimuovi(int i) {
        return sc.remove(i);
    }
    
    protected boolean isDisconnected(Socket socket) throws IOException {
        return socket.getInputStream().read() == -1;
    }

    protected BufferedReader getBuff(Socket t) {
        try {
            return new BufferedReader(new InputStreamReader(t.getInputStream()));
        } catch (IOException ex) {
            System.err.println(MSG.errore_generico);
        }
        return null;
    }
    
    protected void dormi(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException ex) {
            System.err.println(MSG.errore_generico);
        }
    }

    private HashMap<String, Metodo> initAllMethods() {
        
        //SetNome SET_NOME = new SetName(sc,sc.get(posInteressato));
        //AddClientToLobby ADD_CLIENT = new AddClientToLobby(sc,sc.get(posInteressato));
        
        final HashMap<String, Metodo> map = new HashMap<String, Metodo>(){{
            put(CMD.SETNAME,new SetName());
            put(CMD.ADDCLIENT, new AddToLobby());
            put(CMD.CONNECT,new ConnectAndNotify());
        }};
        
        return map;
    }
    
    /**
     * Ex : /SETNAME flo h h
     * /setname flo
     * 
     * /ADDNAME
     * @param regex 
     * @return  
     */
    public EXIT_CODE __execute(String regex){
        
        String[] split = regex.split(CMD.SEPARATOR);

        for (int i = 0; i < split.length; i++) {
            if(split[i].charAt(0)==CMD.INIT){
                
                String comando = split[i].substring(1, split[i].length());
                
                Metodo metodo = map.get(comando);
                
                if(metodo==null){
                    return COMANDO_INESISTENTE;
                }
                
                final int N_ARG = metodo.getNArgomenti();
                
                if(split.length-1 - i >= N_ARG){
                    
                    String[] parametri = new String[N_ARG];
                    
                    for (int j = 0; j < N_ARG; j++) {
                        parametri[j] = split[i+j+1];
                    }
                    
                    metodo.setInfos(parametri);
                    metodo.setSc(sc);
                    metodo.setInteressato(this.posInteressato);
                    
                    try {
                        metodo.call();
                        return OK;
                    } catch (Exception ex) {
                        return COMANDO_INESISTENTE;
                    }
                    
                }else{
                    return PARAMETRI_INSUFFICENTI;
                }
            }
        }
        
        return NON_UN_COMANDO;
    }
    
    public Cliente getInteressato() {
        return sc.get(posInteressato);
    }

    public void setInteressato(int posInteressato) {
        this.posInteressato = posInteressato;
    }

}
