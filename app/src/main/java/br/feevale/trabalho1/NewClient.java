package br.feevale.trabalho1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

public class NewClient extends AppCompatActivity {

    EditText inputName;
    EditText inputAddress;
    EditText inputEmail;
    DatabaseStructure db;
    RadioGroup radioTypeGroup;
    RadioButton radioTypeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);

        db = new DatabaseStructure(this);


        inputName = (EditText) findViewById(R.id.txtClientName);
        inputAddress = (EditText) findViewById(R.id.txtClientAddress);
        inputEmail = (EditText) findViewById(R.id.txtClientEmail);
        radioTypeGroup = (RadioGroup) findViewById(R.id.radioGroupType);

    }

    public void addClient(View v){

        Client c1 = new Client();
        c1.setName(inputName.getText().toString());
        c1.setAddress(inputAddress.getText().toString());
        c1.setContactEmail(inputEmail.getText().toString());


        int selectedId = radioTypeGroup.getCheckedRadioButtonId();
        radioTypeButton = (RadioButton) findViewById(selectedId);
        String tipoSelecionado = radioTypeButton.getText().toString();

        int tipo = 20;

        if(tipoSelecionado.equals("Bar")){
            tipo = 10;
        }

        c1.setType(tipo);
        Long sId = db.addClient(c1);
        Log.d("IDS", "CLIENT: " + sId.toString());
        finish();
    }
}
