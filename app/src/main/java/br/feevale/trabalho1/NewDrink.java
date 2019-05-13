package br.feevale.trabalho1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class NewDrink extends AppCompatActivity {

    EditText inputName;
    EditText inputVolume;
    EditText inputPrice;
    DatabaseStructure db;
    RadioGroup radioTypeGroup;
    RadioButton radioTypeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_drink);

        db = new DatabaseStructure(this);

        inputName = (EditText) findViewById(R.id.txtInputName);
        inputVolume = (EditText) findViewById(R.id.txtInputVolume);
        inputPrice = (EditText) findViewById(R.id.txtInputPrice);
        radioTypeGroup = (RadioGroup) findViewById(R.id.radioGroup);

    }

    public void addNewDrink(View v){
        Drink d = new Drink();

        d.setName(inputName.getText().toString());
        d.setVolume(Integer.parseInt(inputVolume.getText().toString()));
        d.setPrice(Double.parseDouble(inputPrice.getText().toString()));

        int selectedId = radioTypeGroup.getCheckedRadioButtonId();
        radioTypeButton = (RadioButton) findViewById(selectedId);
        String tipoSelecionado = radioTypeButton.getText().toString();

        boolean tipo = true;

        if(tipoSelecionado.equals("Não")){
            tipo = false;
        }

        d.setAlcoolic(tipo);

        Long sId = db.addDrink(d);
        Log.d("DRINK INSERT","ID " + sId);
        Log.d("VOLUME ON OBJECT", String.valueOf(d.getVolume()));
        Log.d("VOLUME INPUT",inputVolume.getText().toString());

        finish();

    }
}
