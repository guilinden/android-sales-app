package br.feevale.trabalho1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditDrinkActivity extends AppCompatActivity {

    EditText inputName;
    EditText inputVolume;
    EditText inputPrice;
    DatabaseStructure db;
    RadioGroup radioTypeGroup;
    RadioButton radioTypeButton;
    Drink d1;
    Intent it;
    RadioButton radioAlcoolic;
    RadioButton radioNonAlcoolic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_drink);

        db = new DatabaseStructure(this);

        inputName = (EditText) findViewById(R.id.txtInputName);
        inputVolume = (EditText) findViewById(R.id.txtInputVolume);
        inputPrice = (EditText) findViewById(R.id.txtInputPrice);
        radioTypeGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioAlcoolic = (RadioButton) findViewById(R.id.radioAlcoolic);
        radioNonAlcoolic = (RadioButton) findViewById(R.id.radioNonAlcoolic);

        it = getIntent();

        d1 = new Drink();
        d1 = db.getDrink(it.getLongExtra("ID",0));

        inputName.setText(d1.getName());
        inputVolume.setText(Integer.toString(d1.getVolume()));
        inputPrice.setText(Double.toString(d1.getPrice()));

        if(d1.isAlcoolic()){
            radioTypeGroup.check(radioAlcoolic.getId());

        }
        else{
            radioTypeGroup.check(radioNonAlcoolic.getId());
        }
    }

    public void editDrink(View v){

        d1.setName(inputName.getText().toString());
        d1.setVolume(Integer.parseInt(inputVolume.getText().toString()));
        d1.setPrice(Double.parseDouble(inputPrice.getText().toString()));

        int selectedId = radioTypeGroup.getCheckedRadioButtonId();
        radioTypeButton = (RadioButton) findViewById(selectedId);
        String tipoSelecionado = radioTypeButton.getText().toString();

        boolean tipo = true;

        if(tipoSelecionado.equals("Não")){
            tipo = false;
        }

        d1.setAlcoolic(tipo);
        db.updateDrink(d1);
        finish();
    }
}
