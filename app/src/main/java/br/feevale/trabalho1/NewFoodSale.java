package br.feevale.trabalho1;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class NewFoodSale extends AppCompatActivity {

    TextView txtClientName;
    TextView txtFoodsPrice;
    TextView txtTotalPrice;
    DatabaseStructure db;
    FoodListAdapter foodListAdapter;
    ListView listFood;
    Intent it;
    long id;
    Client c1;
    Long clientId;
    Sale s;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food_sale);

        txtClientName = (TextView) findViewById(R.id.txtClientName);
        txtFoodsPrice = (TextView) findViewById(R.id.txtDrinksPrice);
        txtTotalPrice  = (TextView)  findViewById(R.id.txtTotalPrice);


        it = getIntent();
        db = new DatabaseStructure(this);

        id = it.getLongExtra("ID",0);

        listFood = (ListView) findViewById(R.id.listDrinks);

        foodListAdapter = new FoodListAdapter(getBaseContext(),db);
        listFood.setAdapter(foodListAdapter);


        clientId = it.getLongExtra("ID",0);

        c1 = new Client();
        c1 = db.getClient(it.getLongExtra("ID",0));

        Log.d("CLIENT NAME",c1.getName());

        txtClientName.setText(c1.getName());


        double price = getFoodPrices();
        txtFoodsPrice.setText(String.format("R$%.2f", price));

        double totalPrice = price + getDrinkPrices();
        txtTotalPrice.setText(String.format("R$%.2f", totalPrice));


        listFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                s = new Sale();
                s.setClient(clientId);
                s.setFood(id);
                it.putExtra("ID",id);
                Long sId = db.addFoodSale(s);
                Log.d("DRINK INSERT","ID " + sId);
                double price = getFoodPrices();
                txtFoodsPrice.setText(String.format("R$%.2f",price));
                double totalPrice = price + getDrinkPrices();
                txtTotalPrice.setText(String.format("R$%.2f", totalPrice));
            }
        });
    }

    private double getDrinkPrices(){
        List<Sale> sales;
        sales = db.getDrinkSales(id);

        Double price = 0.0;
        int i = 0;
        Drink drinkforPrice;
        Log.d("SIZE ARRAY", String.valueOf(sales.size()));
        if(sales.size() > 0) {
            while (i < sales.size()) {
                long foodId = sales.get(i).getDrink();
                Log.d("FOOD ID", String.valueOf(foodId));
                drinkforPrice = db.getDrink(foodId);
                price = price + drinkforPrice.getPrice();
                i++;
            }
        }
        return price;
    }

    private double getFoodPrices(){
        List<Sale> sales;
        sales = db.getFoodSales(id);

        Double price = 0.0;
        int i = 0;
        Food foodforPrice;

        while (i < sales.size()) {
            long foodId = sales.get(i).getFood();
            Log.d("FOOD ID", String.valueOf(foodId));
            foodforPrice = db.getFood(foodId);
            price = price + foodforPrice.getPrice();
            i++;
        }
        return price;
    }

}
