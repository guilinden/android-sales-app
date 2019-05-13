package br.feevale.trabalho1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    DatabaseStructure db;
    ListView listClients;
    ClientListAdapter clientAdapter;
    public static final int CODE_MAIN_ACTIVITY = 1010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listClients = (ListView) findViewById(R.id.listClients);

        db = new DatabaseStructure(this);
        clientAdapter = new ClientListAdapter(getBaseContext(),db);
        listClients.setAdapter(clientAdapter);


        listClients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                boolean status = db.deleteClient(id);
                clientAdapter.notifyDataSetChanged();
                return status;
            }
        });

        listClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it  = new Intent(getBaseContext(), EditClientActivity.class);
                it.putExtra("ID",id);
                startActivityForResult(it,CODE_MAIN_ACTIVITY);
            }
        });



    }

    @Override
    public void onResume(){
        super.onResume();
        clientAdapter.notifyDataSetChanged();

    }


    public void newClient(View v){
        Intent it  = new Intent(getBaseContext(), NewClient.class);
        startActivityForResult(it,CODE_MAIN_ACTIVITY);
        clientAdapter.notifyDataSetChanged();
    }

    public void listProducts(View v){
        Intent it  = new Intent(getBaseContext(), selecionarTipoProduto.class);
        startActivityForResult(it,CODE_MAIN_ACTIVITY);
    }

    public void newSale(View v){
        Intent it = new Intent(getBaseContext(),selecionarClienteVenda.class);
        startActivityForResult(it,CODE_MAIN_ACTIVITY);
    }
}
