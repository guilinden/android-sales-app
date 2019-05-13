package br.feevale.trabalho1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListDrink extends AppCompatActivity {

    DatabaseStructure db;
    DrinkListAdapter drinkListAdapter;
    ListView listDrink;
    public static final int CODE_LIST_DRINK_ACTIVITY = 911;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drink);

        db = new DatabaseStructure(this);

        listDrink = (ListView) findViewById(R.id.listDrink);

        drinkListAdapter = new DrinkListAdapter(getBaseContext(),db);
        listDrink.setAdapter(drinkListAdapter);

        listDrink.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                boolean status = db.deleteDrink(id);
                drinkListAdapter.notifyDataSetChanged();
                return status;
            }
        });

        listDrink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it  = new Intent(getBaseContext(), EditDrinkActivity.class);
                it.putExtra("ID",id);
                startActivityForResult(it,CODE_LIST_DRINK_ACTIVITY);
            }
        });

    }

    public void newDrink(View v){
        Intent it  = new Intent(getBaseContext(), NewDrink.class);
        startActivityForResult(it,CODE_LIST_DRINK_ACTIVITY);
    }

    @Override
    public void onResume(){
        super.onResume();
        drinkListAdapter.notifyDataSetChanged();
    }
}
