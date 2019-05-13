package br.feevale.trabalho1;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class EditClientActivity extends AppCompatActivity {

    TextView inputName;
    TextView inputAddress;
    TextView inputEmail;
    DatabaseStructure db;
    Intent it;
    Client c1;
    RadioGroup radioTypeGroup;
    RadioButton radioBar;
    RadioButton radioRestaurante;
    RadioButton radioTypeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);

        db = new DatabaseStructure(this);

        it = getIntent();

        inputName = (TextView) findViewById(R.id.txtClientName);
        inputAddress = (TextView) findViewById(R.id.txtClientAddress);
        inputEmail = (TextView) findViewById(R.id.txtClientEmail);
        radioTypeGroup = (RadioGroup) findViewById(R.id.radioGroupType);
        radioBar = (RadioButton) findViewById(R.id.radioBar);
        radioRestaurante = (RadioButton) findViewById(R.id.radioRestaurante);


        c1 = new Client();
        c1 = db.getClient(it.getLongExtra("ID",0));

        inputName.setText(c1.getName());
        inputAddress.setText(c1.getAddress());
        inputEmail.setText(c1.getContactEmail());

        if(c1.getType() == 10){
            radioTypeGroup.check(radioBar.getId());

        }
        else{
            radioTypeGroup.check(radioRestaurante.getId());
        }



    }

    public void editClient(View v){

        c1.setId(it.getLongExtra("ID",0));
        c1.setName(inputName.getText().toString());
        c1.setAddress(inputAddress.getText().toString());
        c1.setContactEmail(inputEmail.getText().toString());

        int tipo = 20;

        int selectedId = radioTypeGroup.getCheckedRadioButtonId();
        radioTypeButton = (RadioButton) findViewById(selectedId);
        String tipoSelecionado = radioTypeButton.getText().toString();

        if(tipoSelecionado.equals("Bar")){
            tipo = 10;
        }

        c1.setType(tipo);

        db.updateClient(c1);

        finish();

    }
}
