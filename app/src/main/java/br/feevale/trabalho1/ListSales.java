package br.feevale.trabalho1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListSales extends AppCompatActivity {

    DatabaseStructure db;
    ListDrinkSalesAdapter drinkListAdapter;
    ListFoodSalesAdapter foodListAdapter;
    ListView listDrinks;
    ListView listFoods;
    Intent it;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sales);

        it = getIntent();

        id = it.getLongExtra("ID",0);

        Log.d("CLIENT ID SALES", String.valueOf(id));

        db = new DatabaseStructure(this);

        listDrinks = (ListView) findViewById(R.id.drinkSalesList);
        listFoods = (ListView) findViewById(R.id.foodSalesList);

        drinkListAdapter = new ListDrinkSalesAdapter(getBaseContext(),db,id);
        listDrinks.setAdapter(drinkListAdapter);

        foodListAdapter = new ListFoodSalesAdapter(getBaseContext(),db,id);
        listFoods.setAdapter(foodListAdapter);

        listFoods.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                boolean status = db.deleteFoodSale(id);
                foodListAdapter.notifyDataSetChanged();
                return status;
            }
        });

        listDrinks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                boolean status = db.deleteDrinkSale(id);
                drinkListAdapter.notifyDataSetChanged();
                return status;
            }
        });
    }
}
