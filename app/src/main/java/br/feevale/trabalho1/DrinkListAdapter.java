package br.feevale.trabalho1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DrinkListAdapter extends BaseAdapter {

    DatabaseStructure db;
    Context ctx;
    LayoutInflater inflater;

    public DrinkListAdapter(Context ctx, DatabaseStructure db){
        inflater = LayoutInflater.from(ctx);
        this.db = db;
    }

    @Override
    public int getCount() {
        return db.getDrinks().size();
    }

    @Override
    public Object getItem(int i) {
        return db.getDrinks().get(i);
    }

    @Override
    public long getItemId(int i) {
        return db.getDrinks().get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.activity_drink_list_adapter, viewGroup, false);
        TextView txtName = (TextView) v.findViewById(R.id.txtName);
        TextView txtPrice = (TextView) v.findViewById(R.id.txtPrice);
        TextView txtVolume = (TextView) v.findViewById(R.id.txtVolume);

        Drink f = db.getDrinks().get(i);
        txtName.setText(f.getName());
        txtPrice.setText(Double.toString(f.getPrice()));
        txtVolume.setText(Integer.toString(f.getVolume()));

        Log.d("VOLUME", String.valueOf(f.getVolume()));

        return v;
    }

}
