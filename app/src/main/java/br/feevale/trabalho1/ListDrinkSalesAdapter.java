package br.feevale.trabalho1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListDrinkSalesAdapter extends BaseAdapter {

    DatabaseStructure db;
    Context ctx;
    LayoutInflater inflater;
    Long id;

    public ListDrinkSalesAdapter(Context ctx, DatabaseStructure db,Long id){
        this.id = id;
        inflater = LayoutInflater.from(ctx);
        this.db = db;
    }

    @Override
    public int getCount() {
        return db.getDrinkSales(id).size();
    }

    @Override
    public Object getItem(int i) {
        return db.getDrinkSales(id).get(i);
    }

    @Override
    public long getItemId(int i) {
        return db.getDrinkSales(id).get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.activity_drink_list_adapter, viewGroup, false);
        TextView txtName = (TextView) v.findViewById(R.id.txtName);
        TextView txtPrice = (TextView) v.findViewById(R.id.txtPrice);
        TextView txtVolume = (TextView) v.findViewById(R.id.txtVolume);

        Sale s1 = db.getDrinkSales(id).get(i);
        long drinkId = s1.getDrink();
        Drink d1 = db.getDrink(drinkId);

        txtName.setText(d1.getName());
        txtPrice.setText(Double.toString(d1.getPrice()));
        txtVolume.setText(Integer.toString(d1.getVolume()));

        Log.d("VOLUME", String.valueOf(d1.getVolume()));

        return v;
    }

}
