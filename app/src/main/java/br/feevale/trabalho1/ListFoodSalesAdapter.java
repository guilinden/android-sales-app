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

public class ListFoodSalesAdapter extends BaseAdapter {

    DatabaseStructure db;
    Context ctx;
    LayoutInflater inflater;
    Long id;

    public ListFoodSalesAdapter(Context ctx, DatabaseStructure db,Long id){
        this.id = id;
        inflater = LayoutInflater.from(ctx);
        this.db = db;
    }

    @Override
    public int getCount() {
        return db.getFoodSales(id).size();
    }

    @Override
    public Object getItem(int i) {
        return db.getFoodSales(id).get(i);
    }

    @Override
    public long getItemId(int i) {
        return db.getFoodSales(id).get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.activity_food_list_adapter, viewGroup, false);

        TextView txtFoodName = (TextView) v.findViewById(R.id.txtName);
        TextView txtFoodCalories = (TextView) v.findViewById(R.id.txtCalories);
        TextView txtPrice = (TextView) v.findViewById(R.id.txtPrice);

        Sale s1 = db.getFoodSales(id).get(i);
        long foodId = s1.getFood();
        Food f1 = db.getFood(foodId);

        txtFoodName.setText(f1.getName());
        txtFoodCalories.setText(Integer.toString(f1.getCalories()));
        txtPrice.setText(Double.toString(f1.getPrice()));

        return v;
    }
}
