package main.Serber.pizzeriaExample.cibo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Pizza {

    private ArrayList<Fetta> fette = new ArrayList<>();

    private double prezzo;
    private int nFette;
    private String nomePizza;

    public Pizza(int nFette,double prezzo) {
        this.nFette = nFette;

        for (int i=0;i<nFette;i++){
            fette.add(new Fetta(i));
        }
        
        this.prezzo = prezzo;
    }
    
    public Pizza(){
        this(1,0);
    }
    
    public Pizza(int nFette){
        this(nFette,0);
    }
    
    public Pizza(double prezzo){
        this(1,prezzo);
    }

    public int getnFette() {
        return nFette;
    }

    public String getNomePizza() {
        return nomePizza;
    }

    public void setNomePizza(String nomePizza) {
        this.nomePizza = nomePizza;
    }

    public double getPrezzo() {
        return prezzo;
    }
    
    public void setNewNFette(int newNFette){

        int toRemove = nFette - newNFette;

        for(int i=0;i<toRemove;i++){
            fette.remove(fette.size()-1);
        }

        for(int i=0;i<-toRemove;i++){
            fette.add(new Fetta(i));
        }
    }

    public void aggiungiIngriediente(int nFetta,int ingriendiente ,int nVolte){
        fette.get(nFetta).addIngriediente(new Ingrediente(ingriendiente,nVolte));
    }

    public String getIngredientiStr() {
        Set<Integer> tipiIngriedienti = new LinkedHashSet<>(); //O(1)

        for(int i=0;i<fette.size();i++){
            ArrayList<Ingrediente> ingenti = fette.get(i).getIngriedienti();
            for(int j=0;j<ingenti.size();j++) {
                tipiIngriedienti.add(ingenti.get(j).getTipo());
            }
        }

        String toString = "";

        for(Iterator<Integer> i = tipiIngriedienti.iterator();i.hasNext();){
            toString += Ingredienti.ingriedienti[i.next()] + " ,";
        }

        return toString;
    }
    
    public Ingrediente[] getIngredienti() {
        Set<Integer> tipiIngriedienti = new LinkedHashSet<>(); //O(1)
        
        ArrayList<Ingrediente> ingenti = null;
        
        for(int i=0;i<fette.size();i++){
            ingenti = fette.get(i).getIngriedienti();
            for(int j=0;j<ingenti.size();j++) {
                tipiIngriedienti.add(j);
            }
        }

        Ingrediente[] array = new Ingrediente[tipiIngriedienti.size()];
        int pos = 0;
        for(Iterator<Integer> i = tipiIngriedienti.iterator();i.hasNext();){
            array[pos++] = new Ingrediente(ingenti.get(i.next()).getTipo());
        }

        return array;
    }
}
