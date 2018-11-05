package com.main.fl0rian.dagennarino.cibo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Pizza implements Prodotto {

    private ArrayList<Fetta> fette = new ArrayList<>();

    private Set<Ingrediente> extra = new HashSet<>();
    private Set<Ingrediente> rimossi = new HashSet<>();

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

    // crea clone
    public Pizza(Pizza clone) {
        this.prezzo = clone.getPrezzo();
        this.rimossi = clone.rimossi;
        this.extra = clone.extra;
        this.fette = clone.fette;
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

    @Override
    public String getTipo() {
        return getNomePizza();
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

    public String getIngredientiStr() {
        Set<Integer> tipiIngriedienti = new LinkedHashSet<>(); //O(1)

        for(int i=0;i<fette.size();i++){
            ArrayList<Ingrediente> ingenti = fette.get(i).getIngriedienti();
            for(int j=0;j<ingenti.size();j++) {
                tipiIngriedienti.add(ingenti.get(j).getTipo());
            }
        }

        String toString = "";
        String sep = " ,";

        for(Iterator<Integer> i = tipiIngriedienti.iterator();i.hasNext();){
            toString += Ingredienti.ingriedienti[i.next()] + sep;
        }

        return toString.substring(0,toString.length()-sep.length());
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
            array[pos++] =
                    new Ingrediente(ingenti.get(i.next()).getTipo());
        }

        return array;
    }

    public void setPrezzoPizza(double prezzo) {
        this.prezzo = prezzo;
    }

    public void addIngriediente(Ingrediente t){
        extra.add(t);

        // Addo ad ogni fette aggiungo l ingrediente
        for(int i=0;i<fette.size();i++){
            fette.get(i).addIngriediente(t);
        }

        // se lo aveva precedentemente rimosso ...
        if(rimossi.contains(t)){
            extra.remove(t);
            rimossi.remove(t);

            // tolgo il prezzo pagato per aggiungerlo
            //perche tanto lo aggiunge , sarebbe come riselezionare la pizza...
            incPrezzoExtra(t,-100);
        }else {
            // aggiungere un ingrediente significa andare a modificare le proposte
            //template offerte dal serber ... costa soldi in base a t
            incPrezzoExtra(t, 100);
        }

    }

    private void incPrezzoExtra(Ingrediente t,int percentuale) {
        prezzo += (t.prezzoExtra() * (percentuale/100D));
    }

    public int getKey() {
        return getIngredientiStr().hashCode();
    }

    public boolean containsIngr(Ingrediente ingrediente) {

        Ingrediente[] ing = getIngredienti();

        for (int i=0;i<ing.length;i++){
            if(ingrediente.getTipo()==ing[i].getTipo())return true;
        }

        return false;
    }

    // se trova l ingrediente lo leva
    // anche rimuovere costa ehehehe troppo facile altrimenti
    public boolean removeIngrediente(Ingrediente t) {
        rimossi.add(t);

        boolean almenoUnaFettaLoHa = false;

        for(int i=0;i<fette.size();i++){
            almenoUnaFettaLoHa |= fette.get(i).removeIngrediente(t);
        }

        if(almenoUnaFettaLoHa) {

            System.out.println("ce lha massa : ");
            // Se lo aveva precedentemente aggiunto lui , non gli faccio pagare il doppio...
            if(extra.contains(t)){

                extra.remove(t);
                rimossi.remove(t);

                // tolgo il prezzo pagato per aggiungerlo
                //perche tanto lo rimuove , sarebbe come riselezionare la pizza...
                incPrezzoExtra(t,-100);
            }else {
                // rimuovere un ingrediente significa andare a modificare le proposte
                //template offerte dal serber ... costa soldi proprio aooonnaa sgancia
                incPrezzoExtra(t, 100);
            }
        }

        return almenoUnaFettaLoHa;
    }
}
