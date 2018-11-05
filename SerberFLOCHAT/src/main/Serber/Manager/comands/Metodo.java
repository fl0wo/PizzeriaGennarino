
package main.Serber.Manager.comands;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import main.Serber.Manager.user.Cliente;

/*
Se si intende cambiare il tipo di return del callable di tutti i metodi basta cambiarlo qui
senza dover cambiarlo per ogni singolo metodo

Inoltre voglio che tutti i metodi returnino un boolean (true se l operazione é andata a buon fine false altrimenti=)

*/
public abstract class Metodo implements Callable<Boolean>{
    
    protected ArrayList<Cliente> sc;
    protected int interessato;
    
    protected String[] infos;
    private final int N_ARGOMENTI_RICHIESTI;

    protected Metodo(ArrayList<Cliente> clienti,int interessato,int N_ARGOMENTI_RICHIESTI) {
        this.sc = clienti;
        this.interessato = interessato;
        this.N_ARGOMENTI_RICHIESTI = N_ARGOMENTI_RICHIESTI;
    }
    
    protected Metodo(int N_ARGOMENTI_RICHIESTI){
        this(null,-1,N_ARGOMENTI_RICHIESTI);
    }
    
    public void setInfos(String... infos){
        this.infos = infos;
    }

    public final int getNArgomenti() {
        return N_ARGOMENTI_RICHIESTI;
    }

    public void setSc(ArrayList<Cliente> sc) {
        this.sc = sc;
    }

    public void setInteressato(int interessato) {
        this.interessato = interessato;
    }
    
    
    
}
