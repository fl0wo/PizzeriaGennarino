package main.Serber;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import main.Serber.Manager.user.Cliente;
import main.Serber.Manager.ManagerRangeClient;
import main.Serber.pizzeriaExample.cibo.Ingrediente;
import main.Serber.pizzeriaExample.cibo.MenuPizzeSerber;
import main.Serber.pizzeriaExample.cibo.Pizza;
import main.messages.CMD;
import main.messages.MSG;

/**
 *
 * @author saban
 */
public class HandlerManager {

    private ArrayList<Cliente> sc = new ArrayList<>();
    private ArrayList<ManagerRangeClient> manager; //for real time informations
    private final int NUMBER_OF_THREADS;

    private ExecutorService ex;

    // only for this example
    private PizzeriaManager pm;

    public HandlerManager(final int N_MAX_THREAD_TO_USE) throws InterruptedException, ExecutionException {
        NUMBER_OF_THREADS = N_MAX_THREAD_TO_USE;
        //init();
        pm = new PizzeriaManager();
        pm.initPizzeria();
    }

    void addSocket(Socket tmp) {

        // for pizzeria server :
        pm.sendPizzeriaTo(tmp);

        // uncomment those 3 lines for a real time information for the client.
        //Cliente t = new Cliente(tmp);
        //sc.add(t);
        //updateManagers();
        System.out.println(tmp + MSG.connessione_client);
    }

    private void updateManagers() {

        int start = 0;
        int inc = sc.size() / NUMBER_OF_THREADS;
        int end = inc;

        for (int i = 0; i < manager.size() - 1; i++) {
            manager.get(i).setStart(start);
            manager.get(i).setEnd(end);

            start = end;
            end += inc;
        }

        manager.get(manager.size() - 1).setStart(start);
        manager.get(manager.size() - 1).setEnd(sc.size());

    }

    private void init() throws InterruptedException, ExecutionException {

        manager = new ArrayList<>();

        ex = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        int start = 0;
        int inc = sc.size() / NUMBER_OF_THREADS;
        int end = inc;

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            ManagerRangeClient tmp = new ManagerRangeClient(start, end, sc);

            manager.add(tmp);
            Future<String> f = ex.submit(tmp);

            start = end;
            end += inc;
        }

        ex.shutdown();
    }

    //Inner class (it needs the ex executor)
    class PizzeriaManager {

        //singleton
        private final Pizzeria pizzeria = Pizzeria.getInstance();

        private String aStringa;

        public PizzeriaManager() {
            initPizzeria();

            ex.execute(() -> {
                convertToString();
            });
        }

        private void sendPizzeriaTo(Socket tmp) {
            ex.execute(() -> {
                PrintWriter pw  = null;
                try {
                    pw = new PrintWriter(tmp.getOutputStream(), true);
                    pw.println(aStringa);
                } catch (IOException ex) {
                    System.err.println(MSG.errore_generico);
                } finally {
                    pw.close();
                }
            });
        }

        private void initPizzeria() {
            ex = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        }

        private void convertToString() {
            JsonObjectBuilder jco = Json.createObjectBuilder();

            JsonArrayBuilder array_pizze = Json.createArrayBuilder();
            JsonObjectBuilder pizza;

            ArrayList<Pizza> pizze = Pizzeria.getInstance().getListino();

            for (int i = 0; i < pizze.size(); i++) {

                pizza = Json.createObjectBuilder();

                pizza.add(CMD.NOME_PIZZA, pizze.get(i).getNomePizza());

                Ingrediente[] ingredienti_pizza = pizze.get(i).getIngredienti();

                JsonArrayBuilder array_ingredienti = Json.createArrayBuilder();

                for (int j = 0; j < ingredienti_pizza.length; j++) {
                    array_ingredienti.add(ingredienti_pizza[j].getTipo());
                }

                pizza.add(CMD.INGREDIENTI, array_ingredienti);

                pizza.add(CMD.PREZZO_PIZZA, pizze.get(i).getPrezzo());

                pizza.add(CMD.N_FETTE, pizze.get(i).getnFette());

                array_pizze.add(pizza);
            }

            jco.add(CMD.PIZZE, array_pizze);

            JsonObject jo = jco.build();

            aStringa = jo.toString();
            System.out.println(jo.toString());
        }

    }

}
// Nested class every PizzeriaManager needs that
// [SINGLETONE]

class Pizzeria {

    private Pizzeria() {
    }

    private static final Pizzeria pizzeria = new Pizzeria();

    public static Pizzeria getInstance() {
        return pizzeria;
    }

    protected ArrayList<Pizza> getListino() {
        return MenuPizzeSerber.getListino();
    }
}
