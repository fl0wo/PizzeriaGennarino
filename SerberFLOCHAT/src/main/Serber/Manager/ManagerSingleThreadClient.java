package main.Serber.Manager;

import main.Serber.Manager.user.Cliente;
import java.util.ArrayList;

/**
 *
 * @author saban
 */
public class ManagerSingleThreadClient extends ManagerClient<String>{

    public ManagerSingleThreadClient(ArrayList<Cliente> sockets) {
        super(sockets);
    }
    
    @Override
    public String call() throws Exception {
        return "";
    }
    
}
