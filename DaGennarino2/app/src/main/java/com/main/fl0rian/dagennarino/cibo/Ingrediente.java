package com.main.fl0rian.dagennarino.cibo;

public class Ingrediente {

    private int tipoIngriediente;
    private int nVolte;

    public Ingrediente(int tipoIngriediente, int nVolte) {
        this.tipoIngriediente = tipoIngriediente;
        this.nVolte = nVolte;
    }

    public Ingrediente(int tipo) {
        this(tipo,1);
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "tipoIngriediente='" + tipoIngriediente + '\'' +
                ", nVolte=" + nVolte +
                '}';
    }

    public int getTipo() {
        return tipoIngriediente;
    }

    public double prezzoExtra() {
        // per questo esempio banale tutti gli ingredienti
        // se aggiunti extra costano 50 cent...
        // per evitare cio basta aggiungere un parametor al costruttore
        return .5D;
    }
}
