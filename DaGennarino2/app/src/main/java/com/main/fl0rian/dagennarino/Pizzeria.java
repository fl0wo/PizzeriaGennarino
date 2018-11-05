package com.main.fl0rian.dagennarino;

import com.main.fl0rian.dagennarino.cibo.Ingredienti;
import com.main.fl0rian.dagennarino.cibo.Pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class Pizzeria {
/*
    private static final int POCA = 1;
    private static final int GIUSTA = 2;
    private static final int ABBONDANTE = 3;

    private static final int SEI = 6;
    private static final int QUATTRO = 4;
*/

    public HashMap<String,Pizza> pizze = new HashMap<>();

    public void add(Pizza p){
        System.out.println(p.getNomePizza());
        pizze.put(p.getNomePizza(),p);
    }

    public Pizza[] getAllPizze() {

        Set<String> set = this.pizze.keySet();

        Pizza[] pizze = new Pizza[set.size()];

        int cnt = 0;

        for (Iterator it = set.iterator();it.hasNext();){
            Pizza tmp = this.pizze.get(it.next());
            pizze[cnt++] = tmp;
        }

        return pizze;
    }
}
