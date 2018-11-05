package main.messages;
/**
 *
 * @author saban
 */
public class CMD {
    // see HashMap of ManagerClient 
    
    public static char INIT = '/';
    public static String SEPARATOR = " ";
    
    
////////////////////////////////////////////////////////////////////////////////////    for serber //////////////////////////////
    
        //login comands
        public static String CONNECT = "CONNECT";
        public static String SETNAME = "SETNAME";
        public static String SETPASS = "SETPASS";

        //lobby comands
        public static String MAKELOBBY = "MAKELOBBY";
        public static String ADDCLIENT = "ADDTOLOBBY";

        //chat comands
        public static String SENDTO = "SENDTO";
        public static String SENDINTO = "SENDINTO";

        public static String[] comands = {
            SETNAME,SETPASS,MAKELOBBY,ADDCLIENT,SENDTO,SENDINTO
        };
        
        
////////////////////////////////////////////////////////////////////////////////////    for client //////////////////////////////
        
        public static String CONNECT_NOTIFY = "NOTIFY";
        public static String SET_NAME = "SETNAME"; // setname from to
        public static String PIZZE = "PIZZE";
        public static String NOME_PIZZA = "NOME_PIZZA";
        public static String INGREDIENTI = "INGRIENDIENTI";
        public static String PREZZO_PIZZA = "PREZZO_PIZZA";
        public static String N_FETTE = "N_FETTE";
    
}
