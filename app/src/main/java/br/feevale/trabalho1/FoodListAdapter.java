package br.feevale.trabalho1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FoodListAdapter extends BaseAdapter {

    DatabaseStructure db;
    Context ctx;
    LayoutInflater inflater;

    public FoodListAdapter(Context ctx, DatabaseStructure db){
        inflater = LayoutInflater.from(ctx);
        this.db = db;
    }

    @Override
    public int getCount() {
        return db.getFoods().size();
    }

    @Override
    public Object getItem(int i) {
        return db.getFoods().get(i);
    }

    @Override
    public long getItemId(int i) {
        return db.getFoods().get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.activity_food_list_adapter, viewGroup, false);
        TextView txtFoodName = (TextView) v.findViewById(R.id.txtName);
        TextView txtFoodCalories = (TextView) v.findViewById(R.id.txtCalories);
        TextView txtPrice = (TextView) v.findViewById(R.id.txtPrice);

        Food f = db.getFoods().get(i);
        txtFoodName.setText(f.getName());
        txtFoodCalories.setText(Integer.toString(f.getCalories()));
        txtPrice.setText(Double.toString(f.getPrice()));

        return v;
    }
}
