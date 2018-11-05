package main.Serber.pizzeriaExample.cibo;

public class Ingrediente {

    private int tipoIngriediente;
    private int nVolte;

    public Ingrediente(int tipoIngriediente, int nVolte) {
        this.tipoIngriediente = tipoIngriediente;
        this.nVolte = nVolte;
    }

    public Ingrediente(int tipoIngriediente) {
        this(tipoIngriediente,0);
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
}
