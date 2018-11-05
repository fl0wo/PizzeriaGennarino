package com.main.fl0rian.dagennarino;

import android.util.JsonReader;

import com.main.fl0rian.dagennarino.MSG.CMD;
import com.main.fl0rian.dagennarino.cibo.Ingrediente;
import com.main.fl0rian.dagennarino.cibo.Ingredienti;
import com.main.fl0rian.dagennarino.cibo.Pizza;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class ClientConnection implements Runnable{

    public static Socket clientSocket;

    private Pizzeria pizzeria = new Pizzeria();

    private OnPizzeriaReadyListener listener = null;

    // port of the serber :D
    private static final int port = 6666;

    // made with no-ip dinamic dns
    private static final String ip = "flochat.ddns.net";

    @Override
    public void run() {
        System.out.println("Tryng to connect at \nip : "+ ip +"\nport : "+port);

        try {
            clientSocket = new Socket(ip, port); } catch (IOException e) {
        }

        System.out.println("Connected :D");

        try {
            readInfos();
            clientSocket.close();
        } catch (IOException e) {
        }
    }

    private void readInfos() throws IOException {
        BufferedReader inFromSerber = null;

        try {
            inFromSerber = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            readInfos();
            return;
        }

        //while (true) {
        try {
            String str = inFromSerber.readLine();

            final JSONObject obj = new JSONObject(str);
            final JSONArray pizze = obj.getJSONArray(CMD.PIZZE);

            final int n = pizze.length();
            for (int i = 0; i < n; ++i) {

                final Pizza pizza_obj = new Pizza();

                final JSONObject info = (JSONObject) pizze.get(i);

                pizza_obj.setNomePizza(info.getString(CMD.NOME_PIZZA).toUpperCase());
                pizza_obj.setPrezzoPizza(info.getDouble(CMD.PREZZO_PIZZA));
                pizza_obj.setNewNFette(info.getInt(CMD.N_FETTE));

                final JSONArray ingriedienti = info.getJSONArray(CMD.INGREDIENTI);

                for (int j = 0; j < ingriedienti.length(); ++j) {
                    final Integer ingrediente = (Integer) ingriedienti.get(j);
                    pizza_obj.addIngriediente(new Ingrediente(ingrediente));
                }

                pizzeria.add(pizza_obj);

            }

        } catch (IOException ex) {
            System.err.println("rippppp");
        } catch (JSONException e) {
            System.err.println("rip " + e.getMessage());
        }

        listener.printPizzes();

        inFromSerber.close();
    }

    public void setOnPizzeriaReadyListener(OnPizzeriaReadyListener onPizzeriaReadyListener){
        this.listener = onPizzeriaReadyListener;
    }

    public Pizzeria getPizzeria() {
        return pizzeria;
    }
}
