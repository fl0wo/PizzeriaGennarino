package main;

import main.Serber.TCPSerber;
import java.io.IOException;
import main.messages.MSG;

public class Main  {
    public static void main(String[] args){
        try {
            TCPSerber serber = new TCPSerber(6666);
            serber.start();
        } catch (IOException ex) {
            System.err.println(MSG.imp_serber_avv);
        }
    }

}
