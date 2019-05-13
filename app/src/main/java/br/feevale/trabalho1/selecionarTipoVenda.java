package br.feevale.trabalho1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class selecionarTipoVenda extends AppCompatActivity {

    Intent it;
    Long id;
    TextView txtFinalValue;
    DatabaseStructure db;
    public final int CODE_SELECT_SELL_TYPE_ACTIVITY = 40;
    double price = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_tipo_venda);

        txtFinalValue = (TextView) findViewById(R.id.txtTotalValue);
        db = new DatabaseStructure(this);
        it = getIntent();
        id = it.getLongExtra("ID",0);
        Log.d("ID ON SELECT SELL TYPE", String.valueOf(id));


        price = getDrinkPrices();
        price = price + getFoodPrices();

        txtFinalValue.setText(String.format("R$%.2f",price));

    }

    public void foodSale(View v){
        Intent it  = new Intent(getBaseContext(), NewFoodSale.class);
        it.putExtra("ID",id);
        startActivityForResult(it,CODE_SELECT_SELL_TYPE_ACTIVITY);
    }

    public void drinkSale(View v){
        Intent it  = new Intent(getBaseContext(), NewDrinkSale.class);
        it.putExtra("ID",id);
        startActivityForResult(it,CODE_SELECT_SELL_TYPE_ACTIVITY);
    }

    @Override
    public void onResume(){
        super.onResume();
        price = getDrinkPrices();
        price = price + getFoodPrices();

        txtFinalValue.setText(String.format("R$%.2f",price));

    }

    public void viewSales(View v){
        Intent it  = new Intent(getBaseContext(), ListSales.class);
        it.putExtra("ID",id);
        startActivityForResult(it,CODE_SELECT_SELL_TYPE_ACTIVITY);
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
