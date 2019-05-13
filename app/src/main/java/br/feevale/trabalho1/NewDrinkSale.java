package br.feevale.trabalho1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class NewDrinkSale extends AppCompatActivity {

    TextView txtClientName;
    TextView txtDrinksPrice;
    TextView txtTotalPrice;
    DatabaseStructure db;
    DrinkListAdapter drinkListAdapter;
    ListView listDrink;
    Intent it;
    long id;
    Client c1;
    Long clientId;
    Sale s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_drink_sale);

        txtClientName = (TextView) findViewById(R.id.txtClientName);
        txtDrinksPrice = (TextView) findViewById(R.id.txtDrinksPrice);
        txtTotalPrice  = (TextView)  findViewById(R.id.txtTotalPrice);

        db = new DatabaseStructure(this);
        it = getIntent();
        id = it.getLongExtra("ID",0);

        listDrink = (ListView) findViewById(R.id.listDrinks);

        drinkListAdapter = new DrinkListAdapter(getBaseContext(),db);
        listDrink.setAdapter(drinkListAdapter);


        clientId = it.getLongExtra("ID",0);

        c1 = new Client();
        c1 = db.getClient(it.getLongExtra("ID",0));

        Log.d("CLIENT NAME",c1.getName());

        txtClientName.setText(c1.getName());


        double price = getDrinkPrices();
        txtDrinksPrice.setText(String.format("R$%.2f", price));

        double totalPrice = price + getFoodPrices();
        txtTotalPrice.setText(String.format("R$%.2f", totalPrice));

        listDrink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                s = new Sale();
                s.setClient(clientId);
                s.setDrink(id);
                it.putExtra("ID",id);
                Long sId = db.addDrinkSale(s);
                Log.d("DRINK INSERT","ID " + sId);
                double price = getDrinkPrices();
                txtDrinksPrice.setText(String.format("R$%.2f",price));

                double totalPrice = price + getFoodPrices();
                txtTotalPrice.setText(String.format("R$%.2f", totalPrice));
            }
        });
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
}
