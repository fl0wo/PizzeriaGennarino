package com.main.fl0rian.dagennarino.cibo;

import java.util.ArrayList;

public class Fetta {

    //NMAX INGREDIENTI DIPENDE DA QUANTO GRANDE é  L N FETTE

    private ArrayList<Ingrediente> ingredienti = new ArrayList<>();
    private int nFetta;

    public Fetta(int nFetta) {
        this.nFetta = nFetta;
    }

    public boolean addIngriediente(Ingrediente e){
        return ingredienti.add(e);
    }

    public ArrayList<Ingrediente> getIngriedienti() {
        return ingredienti;
    }

    public boolean removeIngrediente(Ingrediente t) {
        return ingredienti.remove(t);
    }
}
