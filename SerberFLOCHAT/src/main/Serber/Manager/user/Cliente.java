package main.Serber.Manager.user;

import java.net.Socket;

/**
 *
 * @author saban
 */
public class Cliente {
    
    private static String N_CURR;
    private static int N_ =0;
    
    private String defaultName;
    private String nomeCliente = "";
    private Socket t;
    private boolean isConnected = false;
    
    public Cliente(String nomeCliente,Socket t) {
        this.nomeCliente = nomeCliente;
        this.t = t;
        this.defaultName = nomeCliente;
    }

    public Cliente(Socket tmp) {
        this(inc(),tmp);
    }

    public boolean hasNameSetted() {
        return !nomeCliente.equals(defaultName);
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }

    //if had called the /CONNECT method
    public boolean isConnected(){
        return  isConnected;
    }

    public void setHasConnected(boolean isConnected) {
        this.isConnected |= isConnected;
    }
    
    public Socket getSocket() {
        return t;
    }

    public void setNome(String newNome) {
        this.nomeCliente = newNome;
    }
    
    static String inc(){
        Cliente.N_CURR = String.format("%05d",N_);
        N_++;
        return Cliente.N_CURR;
    }
    
    
}
