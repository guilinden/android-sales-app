package br.feevale.trabalho1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class NewFood extends AppCompatActivity {

    EditText inputName;
    EditText inputCalories;
    EditText inputExpirationDate;
    EditText inputPrice;
    DatabaseStructure db;
    RadioGroup radioTypeGroup;
    RadioButton radioTypeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);

        db = new DatabaseStructure(this);

        inputName = (EditText) findViewById(R.id.txtInputName);
        inputCalories = (EditText) findViewById(R.id.txtInputCalories);
        inputExpirationDate = (EditText) findViewById(R.id.txtInputExpirationDate);
        inputPrice = (EditText) findViewById(R.id.txtInputPrice);
        radioTypeGroup = (RadioGroup) findViewById(R.id.radioGroupType);


    }

    public void newFood(View v){
        Food f = new Food();

        f.setName(inputName.getText().toString());
        f.setCalories(Integer.parseInt(inputCalories.getText().toString()));
        f.setExpirationDate(inputExpirationDate.getText().toString());
        f.setPrice(Double.parseDouble(inputPrice.getText().toString()));

        int selectedId = radioTypeGroup.getCheckedRadioButtonId();
        radioTypeButton = (RadioButton) findViewById(selectedId);
        String tipoSelecionado = radioTypeButton.getText().toString();

        int tipo = 20;

        if(tipoSelecionado.equals("Quente")){
            tipo = 10;
        }

        f.setType(tipo);

        Long sId = db.addFood(f);
        Log.d("FOOD INSERT","ID " + sId);
        finish();

    }

}
