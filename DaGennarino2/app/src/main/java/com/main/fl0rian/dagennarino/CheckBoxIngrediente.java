package com.main.fl0rian.dagennarino;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.main.fl0rian.dagennarino.cibo.Ingrediente;

class CheckBoxIngrediente extends RelativeLayout {

    private CheckBox cb;
    private ImageView im;

    private Ingrediente ingrediente;

    private View v;

    public CheckBoxIngrediente(Context context,String nome,int rs,Ingrediente ingrediente) {
        super(context);

        this.ingrediente = ingrediente;

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.v = layoutInflater.inflate(R.layout.check_box_ingrediente, this);

        cb = v.findViewById(R.id.check);
        im = v.findViewById(R.id.img);

        im.setImageResource(rs);
        cb.setText(nome);
    }

    public CheckBox getCb() {
        return cb;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }
}
