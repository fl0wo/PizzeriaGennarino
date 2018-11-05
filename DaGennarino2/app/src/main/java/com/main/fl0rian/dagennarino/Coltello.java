package com.main.fl0rian.dagennarino;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.agilie.circularpicker.presenter.CircularPickerContract;
import com.agilie.circularpicker.ui.view.CircularPickerView;

import java.util.ArrayList;

import kotlin.Unit;

public class Coltello {

    private CircularPickerView seekBar;
    private RelativeLayout customizzaLayout;
    private Context aThis;
    private ImageView target;
    private int nFette;


    private final ArrayList<ImageView> tagli = new ArrayList<>();


    public Coltello(RelativeLayout customizzaLayout, Context aThis, ImageView target, int nFette, int nFetteMax){
        this.customizzaLayout = customizzaLayout;
        this.aThis = aThis;
        this.target = target;
        this.nFette = nFette;

        customizzaLayout.setBackgroundColor(Color.WHITE);

        for (int j=0;j<nFetteMax;j++){
            tagli.add(addTaglio(0,j));
        }

        updateTagli(nFette/2);
    }

    public Coltello(CircularPickerView s, RelativeLayout customizzaLayout, Context aThis, ImageView target, int nFette, int nFetteMax) {
        seekBar = s;
        this.customizzaLayout = customizzaLayout;
        this.aThis = aThis;
        this.target = target;
        this.nFette = nFette;

        customizzaLayout.setBackgroundColor(Color.WHITE);

        for (int j=0;j<nFetteMax;j++){
            tagli.add(addTaglio(0,j));
        }

        updateTagli(nFette/2);
        initSettings(nFette,nFetteMax);
    }

    private void updateTagli(int nFette) {
        float angolo = 360F/(nFette*2);

        this.nFette = nFette;

        for (int j=0;j<nFette;j++){
            tagli.get(j).setRotation((angolo*j)+(90+angolo/2));
        }

        for (int j=0;j<tagli.size()-nFette;j++){
            tagli.get(nFette+j).setRotation((90+angolo/2));
        }


    }

    private ImageView addTaglio(float angolo, int j) {

        ViewGroup.LayoutParams lp = target.getLayoutParams();

        ImageView imageView = new ImageView(aThis);
        imageView.setImageResource(R.drawable.ic_linea);

        imageView.setLayoutParams(lp);
        imageView.setRotation(angolo);

        customizzaLayout.addView(imageView);

        return imageView;
    }

    private void initSettings(int posIniziale, int nFetteMax) {



        seekBar.setMaxLapCount(1);
        seekBar.setGradientAngle(90);
        seekBar.setCurrentValue(posIniziale);
        seekBar.setMaxValue(nFetteMax);
        seekBar.setColor(Color.rgb(255,212,170));

        seekBar.setValueChangedListener(new CircularPickerContract.Behavior.ValueChangedListener() {
            @Override
            public void onValueChanged(int val) {
                if(val%2!=0)val++;
                updateTagli(val/2);
            }
        });
    }

    public int getnFette() {
        return nFette*2;
    }
}
