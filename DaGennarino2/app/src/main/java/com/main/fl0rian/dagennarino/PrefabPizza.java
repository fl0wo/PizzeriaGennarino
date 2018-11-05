package com.main.fl0rian.dagennarino;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.fl0rian.dagennarino.cibo.Pizza;

class PrefabPizza extends RelativeLayout {

    private Pizza pizza;
    private int nPizze = 0;

    private View view;
    private TextView prezzo,nome,nSelected;
    private ImageView tick,add;

    public PrefabPizza(Context context,Pizza p) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = layoutInflater.inflate(R.layout.prefab_pizza, this);

        prezzo = view.findViewById(R.id.prezzo);
        nome = view.findViewById(R.id.nomePizza);
        tick = view.findViewById(R.id.tick_id);
        nSelected = view.findViewById(R.id.nPizze);
        add = view.findViewById(R.id.add_butt);

        onAddListener();

        setPizza(p);
    }


    public boolean eseguiClick(){
        boolean visibility = tick.getVisibility()==View.VISIBLE;

        if(!visibility){
            active();
        }else{
            deactive();
        }

        return !visibility;
    }

    public void active() {
        tick.setVisibility(View.VISIBLE);
        add.setVisibility(View.VISIBLE);

        nPizze++;
        nSelected.post(()->{
            nSelected.setText(nPizze+"");
        });
    }

    public void deactive() {
        tick.setVisibility(View.INVISIBLE);
        add.setVisibility(View.INVISIBLE);
        nSelected.post(()->{
            nSelected.setText("");
        });
        nPizze = 0;
    }

    private void onAddListener() {
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                nPizze++;
                nSelected.post(()->{
                    nSelected.setText(nPizze+"");
                });
            }
        });
    }

    private void setPizza(Pizza pizza) {
        this.pizza = pizza;
        nome.setText(pizza.getNomePizza());
        prezzo.setText(pizza.getPrezzo()+"");
    }

    public int getnPizze(){
        return nPizze;
    }

    public View getView() {
        return view;
    }

    public Pizza getPizza() {
        return pizza;
    }
}
