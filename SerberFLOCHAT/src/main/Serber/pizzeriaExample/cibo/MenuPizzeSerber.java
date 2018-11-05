package main.Serber.pizzeriaExample.cibo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.messages.MSG;

public class MenuPizzeSerber {

    private static final int POCA = 1;
    private static final int GIUSTA = 2;
    private static final int ABBONDANTE = 3;

    private static final int SEI = 6;
    private static final int QUATTRO = 4;

    private static final String path_file = "list_pizze.txt";

    private static ArrayList<Pizza> listino = new ArrayList<>();

    public static ArrayList<Pizza> getListino() {
        if (listino.size() == 0) {
            try {
                readFile();
            } catch (FileNotFoundException ex) {
                System.err.println(MSG.errore_generico);
            } catch (IOException ex) {
                System.err.println(MSG.errore_generico);
            }
        }
        return listino;

    }

    private static void readFile() throws FileNotFoundException, IOException {
        BufferedReader fr = new BufferedReader(new FileReader(path_file));
        String line;

        while ((line = fr.readLine()) != null) {
            String[] split = line.split(";");

            String nome = split[0];
            String ingriedienti = split[1];
            String prezzo = split[2];

            Float f = Float.parseFloat(prezzo);

            listino.add(genPizza(nome, ingriedienti, f));

        }

        /*
        String tmp;
        String array = "{";
        int i = 0;
        for (Iterator it=set.iterator();it.hasNext();i++) {
            tmp = (String) it.next();
            
            array+= ","+"\"" + tmp + "\"" ;
            
            tmp = tmp.replace(" ", "_");
            System.out.println("public static final int "+tmp.toUpperCase() + " = "+ i + ";");
            
            
        }

        System.out.println(array);
         */
    }

    private static Pizza genPizza(String nome, String strIngr, Float f) {
        
        Random r = new Random();    // quantita casuale

        //  printaTuttiIngriedienti(strIngr);
        Pizza p = new Pizza(6,f);
        p.setNomePizza(nome);

        strIngr = strIngr.substring(1, strIngr.length() - 1);//tolgo le ( ) 

        if (strIngr.charAt(0) == '(') {
            strIngr = strIngr.substring(1, strIngr.length());
        }

        if (strIngr.charAt(strIngr.length() - 1) == ')') {
            strIngr = strIngr.substring(0, strIngr.length() - 1);
        }

        String[] split = strIngr.split(",");

        for (int i = 0; i < split.length; i++) {    // per tutti gli ingriedienti
            for (int j = 0; j < 6; j++) {   // per tutte le fette

                String t = split[i];

                while (t.charAt(0) == ' ') {
                    t = t.substring(1);
                }

                int id = Ingredienti.valueOf(t); //id ingrediente
                if (id != -1) {
                    p.aggiungiIngriediente(j, id, r.nextInt(3));
                }
            }
        }

        return p;
    }

    // static Set<String> set = new HashSet<>();
    /*
    private static void printaTuttiIngriedienti(String strIngr) {
        //System.out.println(strIngr);
        
        if(strIngr.charAt(0)=='('){
            strIngr= strIngr.substring(1,strIngr.length());
        }
                
        if(strIngr.charAt(strIngr.length()-1)==')'){
            strIngr= strIngr.substring(0,strIngr.length()-1);
        }
        
        String[] spl = strIngr.split(",");
        
        for (int i = 0; i < spl.length; i++) {
            
            String t = spl[i];
                    
            while(t.charAt(0)==' ')
                t = t.substring(1);
            
            set.add(t);
        }
    }*/
}
