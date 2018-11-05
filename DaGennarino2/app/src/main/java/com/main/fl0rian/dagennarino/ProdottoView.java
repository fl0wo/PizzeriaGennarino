package com.main.fl0rian.dagennarino;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.fl0rian.dagennarino.cibo.Pizza;
import com.main.fl0rian.dagennarino.cibo.Prodotto;

class ProdottoView extends RelativeLayout {

    private View view;

    private TextView n_prodotto,tipo_prodotto,prezzo_prodotto;

    public ProdottoView(Context context, Prodotto prodotto, Integer integer) {
        super(context);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = layoutInflater.inflate(R.layout.prodotto_layout, this);

        n_prodotto = view.findViewById(R.id.nPizza);
        tipo_prodotto = view.findViewById(R.id.tipoPizza);
        prezzo_prodotto = view.findViewById(R.id.totale_pizza);

        n_prodotto.setText(integer+"");

        tipo_prodotto.setText(prodotto.getTipo());
        prezzo_prodotto.setText(prodotto.getPrezzo()+" $ ");

    }

}
