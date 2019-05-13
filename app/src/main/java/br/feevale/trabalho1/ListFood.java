package br.feevale.trabalho1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListFood extends AppCompatActivity {

    DatabaseStructure db;
    FoodListAdapter foodListAdapter;
    ListView listFood;
    public static final int CODE_LIST_FOOD_ACTIVITY = 587;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);

        db = new DatabaseStructure(this);

        listFood = (ListView) findViewById(R.id.listDrinks);

        foodListAdapter = new FoodListAdapter(getBaseContext(),db);
        listFood.setAdapter(foodListAdapter);

        listFood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                boolean status = db.deleteFood(id);
                foodListAdapter.notifyDataSetChanged();
                return status;
            }
        });

        listFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it  = new Intent(getBaseContext(), EditFoodActivity.class);
                it.putExtra("ID",id);
                startActivityForResult(it,CODE_LIST_FOOD_ACTIVITY);
            }
        });
    }


    public void newFood(View v){
        Intent it  = new Intent(getBaseContext(), NewFood.class);
        startActivityForResult(it,CODE_LIST_FOOD_ACTIVITY);
        foodListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume(){
        super.onResume();
        foodListAdapter.notifyDataSetChanged();

    }
}
