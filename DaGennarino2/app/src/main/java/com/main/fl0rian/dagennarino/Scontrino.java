package com.main.fl0rian.dagennarino;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.fl0rian.dagennarino.cibo.Prodotto;

import java.util.ArrayList;
import java.util.Random;

public class Scontrino{

    private ArrayList<Integer> n_prodotto = new ArrayList<>();
    private ArrayList<Prodotto> prodotti = new ArrayList<>();

    private RelativeLayout scontrino_main;
    private TextView num_pizze,prezzo_totale,codice;

    private LinearLayout lln;

    public Scontrino(RelativeLayout scontrino_main, TextView num_pizze, LinearLayout lln, TextView prezzo_totale, TextView codice) {

        this.scontrino_main = scontrino_main;

        this.num_pizze = num_pizze;
        this.prezzo_totale = prezzo_totale;
        this.codice = codice;
        this.lln = lln;

        hide();

        codice.setText(codiceRandom(20)+"");
    }

    private String codiceRandom(int l) {
        String tmp = "";
        Random r = new Random();

        for (int i=0;i<l;i++){
            if(r.nextBoolean())
                tmp += (char) ('A'+(r.nextInt('Z'-'A')));
            else
                tmp += r.nextInt(9);
        }

        return tmp;
    }

    public void add(Prodotto p, int moltiplicatore){
        prodotti.add(p);
        n_prodotto.add(moltiplicatore);
    }

    public void remove(Prodotto p){
        int i = prodotti.indexOf(p);
        prodotti.remove(i);
        n_prodotto.remove(i);
    }

    public double getPrezzo(){
        return getTotalPrice(); // + eventuali saldi
    }

    public void clear() {
        prodotti.clear();
        n_prodotto.clear();
    }

    public void update(){
        this.num_pizze.setText(getNumOfTotalPizzas()+"");
        this.codice.setText(""+codiceRandom(20));
        this.prezzo_totale.setText(getPrezzo()+" $ ");
        codice.setText(codiceRandom(20)+"");

        this.lln.removeAllViews();

        for (int i=0;i<prodotti.size();i++){
            lln.addView(new ProdottoView(lln.getContext(),prodotti.get(i),n_prodotto.get(i)));
        }

    }

    private double getTotalPrice() {
        double prezzo = 0;

        for (int i=0;i<prodotti.size();i++){
            prezzo+= (prodotti.get(i).getPrezzo() * n_prodotto.get(i));
        }

        return prezzo;
    }

    private int getNumOfTotalPizzas() {
        int n=0;

        for (int i=0;i<n_prodotto.size();i++){
            n+=n_prodotto.get(i);
        }

        return n;
    }

    public void show() {
        scontrino_main.setVisibility(View.VISIBLE);
    }

    public void hide() {
        scontrino_main.setVisibility(View.GONE);
    }

    public boolean isEmpty() {
        return prodotti.isEmpty();
    }
}

