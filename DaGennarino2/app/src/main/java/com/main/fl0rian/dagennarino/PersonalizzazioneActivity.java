package com.main.fl0rian.dagennarino;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.main.fl0rian.dagennarino.cibo.Ingrediente;
import com.main.fl0rian.dagennarino.cibo.Ingredienti;
import com.main.fl0rian.dagennarino.cibo.Pizza;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonalizzazioneActivity  extends AppCompatActivity {

    private ClientConnection client;

    private LinearLayout llh,llh_ing;

    private Handler handler = new Handler();

    private PrefabPizza[] prefab2pizze;

    private TextView desc;

    private CheckBoxIngrediente[] ingr;

    //Usare una LinkedList plz
    private ArrayList<PrefabPizza> scelte_selezionate = new ArrayList<>();
    private Scontrino scontrino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalizzazione_activity);

        client = new ClientConnection();

        llh = findViewById(R.id.lista);
        llh_ing = findViewById(R.id.linear_check);

        desc = findViewById(R.id.descrizione);

        initIngr();

        client.setOnPizzeriaReadyListener(() -> {
            Pizzeria p = client.getPizzeria();

            Pizza[] pizz2e = p.getAllPizze();
            prefab2pizze = new PrefabPizza[pizz2e.length];

            for (int i = 0; i < prefab2pizze.length; i++) {
                prefab2pizze[i] = new PrefabPizza(this,pizz2e[i]);
            }

            handler.post(() -> {
                for (int i = 0; i < prefab2pizze.length; i++) {
                    llh.addView(prefab2pizze[i]);
                }
            });
        });

        scontrino = new Scontrino(
                findViewById(R.id.main_scontrino),
                findViewById(R.id.num_pizze),   // TextView
                findViewById(R.id.linear_prodotti), // Linear
                findViewById(R.id.prezzo_totale_fotonico), // TextView
                findViewById(R.id.codice) // TextView
                );

        //communico col server
        new Thread(client).start();
    }



    private void initIngr() {
        ingr = new CheckBoxIngrediente[Ingredienti.ingriedienti.length];

        String[] arrayNomi = Ingredienti.ingriedienti;

        int[] immagini_check = new int[Ingredienti.ingriedienti.length];

        final int tmpx = R.drawable.exitg;

        for (int i=0;i<immagini_check.length;i++){
            immagini_check[i] = tmpx;   // quelle che devo ancora disegnare
        }

        // icone che ho gia
        immagini_check[Ingredienti.POMODORINI] = R.drawable.tomatoxhdpi;
        immagini_check[Ingredienti.BASILICO] = R.drawable.basilxhdpi;
        immagini_check[Ingredienti.FUNGHI] = R.drawable.champxhdpi;
        immagini_check[Ingredienti.MOZZARELLA] = R.drawable.cheesexhdpi;
        immagini_check[Ingredienti.PEPERONCINO] = R.drawable.chillixhdpi;
        immagini_check[Ingredienti.OLIVE] = R.drawable.olivxhdpi;
        immagini_check[Ingredienti.CIPOLLE] = R.drawable.onionxhdpi;
        immagini_check[Ingredienti.SALSICCIA] = R.drawable.paparonixhdpi;
        immagini_check[Ingredienti.PEPERONI] = R.drawable.pepperxhdpi;

        for (int i=0;i<ingr.length;i++){
            final int pos = i;

            CheckBoxIngrediente c = new CheckBoxIngrediente(this,arrayNomi[i],immagini_check[i],new Ingrediente(i,3));

            c.getCb().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!scelte_selezionate.isEmpty()) {
                        // L ultima pizza selezionata é quella che ci interessa
                        PrefabPizza lastModified = scelte_selezionate.get(scelte_selezionate.size() - 1);
                        Pizza t = lastModified.getPizza();

                        Pizza p = new Pizza(t);

                        // Se il CheckBox non era selezionato
                        //(se non era selezionato , dopo il click lo é)
                        if(c.getCb().isChecked()){
                            // se l ultima pizza in qualche fetta non contine quell'ingrediente
                           if(!p.containsIngr(c.getIngrediente())){
                                //lo aggiungo , incrementando il prezzo della pizza , in base al tipo di ingrediente
                               p.addIngriediente(c.getIngrediente());

                               reportModifica(c.getIngrediente(),false,t);
                           }
                        }else{
                            // se quindi voglio rimuovere un ingrediente
                            if(p.removeIngrediente(c.getIngrediente())){
                                reportModifica(c.getIngrediente(),true,t);
                            }
                        }

                    }
                }
            });

            ingr[i] = c;
            llh_ing.addView(ingr[i]);
        }

    }

    private void reportModifica(Ingrediente ingrediente,boolean rimozione,Pizza daEditare) {
        new AlertDialog.Builder(this)
                .setTitle(rimozione ? "Rimozione Ingrediente" : "Aggiunta Ingrediente")
                .setMessage("[COSTO : "+ingrediente.prezzoExtra() +" $ ]\nSi intende continuare?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(rimozione){
                            daEditare.removeIngrediente(ingrediente);
                            Toast.makeText(getApplicationContext(), "Ingrediente "+ Ingredienti.ingriedienti[ingrediente.getTipo()] + " rimosso! ( + "+ingrediente.prezzoExtra(), Toast.LENGTH_SHORT).show();
                        }else{
                            daEditare.addIngriediente(ingrediente);
                            Toast.makeText(getApplicationContext(), "Ingrediente "+ Ingredienti.ingriedienti[ingrediente.getTipo()] + " aggiunto! ( + "+ingrediente.prezzoExtra(), Toast.LENGTH_SHORT).show();
                        }

                        // qualsiasi sia la modifica , aggiorno la grafica stampando le nuove informazioni relative a quella pizza
                        handler.post(()->{
                            desc.setText(daEditare.getNomePizza()+"\n"+daEditare.getIngredientiStr()+"\n"+daEditare.getPrezzo() +" $ ");
                        });
                    }
                }).setNegativeButton("No", null).show();

    }

    public void backToPizzas(View v) {
        scontrino.hide();
    }

    public void clearScelte(View v){
        scontrino.clear();

        for(int i = 0;i<scelte_selezionate.size();i++){
            scelte_selezionate.get(i).deactive();
        }

        scelte_selezionate.clear();
    }

    public void showBill(View v) {
        scontrino.clear();

        for(int i=0;i<scelte_selezionate.size();i++)
            scontrino.add(scelte_selezionate.get(i).getPizza(),scelte_selezionate.get(i).getnPizze());

        scontrino.update();
        scontrino.show();
    }

    public void click(View v){
        int indicePrefabPizza = llh.indexOfChild((View) v.getParent());
        eseguiClick(prefab2pizze[indicePrefabPizza]);
    }

    private void eseguiClick(PrefabPizza p) {

        final Ingrediente[] ingredienti = p.getPizza().getIngredienti();

        //se avevo gia cliccato la pizza
        if(p.eseguiClick())
            scelte_selezionate.add(p);
        else
            scelte_selezionate.remove(p);

        handler.post(()->{
            for(int i=0;i<ingr.length;i++)
                if(ingr[i]!=null)
                    if(ingr[i].getCb().isChecked())
                        ingr[i].getCb().setChecked(false);


            for(int i=0;i<ingredienti.length;i++) {

                if (ingr[ingredienti[i].getTipo()] != null)
                    if (!ingr[ingredienti[i].getTipo()].getCb().isChecked())
                        ingr[ingredienti[i].getTipo()].getCb().setChecked(true);

            }

            desc.setText(p.getPizza().getNomePizza()+"\n"+p.getPizza().getIngredientiStr()+"\n"+p.getPizza().getPrezzo() +" $ ");
        });
    }

}

interface OnPizzeriaReadyListener{
    void printPizzes();
}



