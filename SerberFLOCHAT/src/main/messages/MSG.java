/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.messages;

import java.net.Socket;

/**
 *
 * @author saban
 */
public class MSG {
    public static final String connessione_client = "HAS CONNECTED";
    
    public static final String imp_serber_avv = "CANT START THE SERBER . . .";
    public static final String imp_client_conn = "CANT CONNECT THE CLIENT . . .";
    public static final String errore_generico = "ERROR!";
    
    public static final String imp_serber_chius = "CANT CLOSE THE SERBER . . .";
    public static final String disconnessione_imprevista = "DISCONNECTION BY CLIENT . . .";
    
    public static final String[] exit_code_msgs = {
      "COMAND DOESN'T EXIST"  ,
        "NOT SUFFICENTS ARGOUMENTS",
        "NOT A COMAND",
        "SYNTAX ERROR",
        "SUCCESFULLY EXECUTED",
        "NOT CORRECT ARGOUMENTS TYPE"
    };
    
}
