/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Serber.Manager.comands;

import java.util.ArrayList;
import main.Serber.Manager.user.Cliente;

/**
 *
 * @author saban
 */
public class AddToLobby extends Metodo{

    private static final int N_PAR = 2;
    
    public AddToLobby(final ArrayList<Cliente> clienti,int c) {
        super(clienti,c,N_PAR);
    }

    public AddToLobby() {
        super(N_PAR);
    }
    
    @Override
    public Boolean call() throws Exception {
        sc.add(sc.get(interessato));
        return true;
    }
}
