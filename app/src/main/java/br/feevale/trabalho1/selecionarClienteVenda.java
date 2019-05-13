package br.feevale.trabalho1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class selecionarClienteVenda extends AppCompatActivity {

    DatabaseStructure db;
    ListView listClients;
    ClientListAdapter clientAdapter;
    public static final int CODE_SELECT_CLIENT_ACTIVITY = 888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_cliente_venda);

        listClients = (ListView) findViewById(R.id.listClients);

        db = new DatabaseStructure(this);
        clientAdapter = new ClientListAdapter(getBaseContext(),db);
        listClients.setAdapter(clientAdapter);

        listClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it  = new Intent(getBaseContext(), selecionarTipoVenda.class);
                it.putExtra("ID",id);
                startActivityForResult(it,CODE_SELECT_CLIENT_ACTIVITY);
            }
        });


    }
}
