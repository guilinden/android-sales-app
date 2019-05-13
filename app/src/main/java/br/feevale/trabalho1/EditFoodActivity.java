package br.feevale.trabalho1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditFoodActivity extends AppCompatActivity {

    EditText inputName;
    EditText inputCalories;
    EditText inputExpirationDate;
    EditText inputPrice;
    DatabaseStructure db;
    RadioGroup radioTypeGroup;
    RadioButton radioTypeButton;
    Food f1;
    Intent it;
    RadioButton radioHot;
    RadioButton radioCold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        db = new DatabaseStructure(this);

        it = getIntent();

        inputName = (EditText) findViewById(R.id.txtInputName);
        inputCalories = (EditText) findViewById(R.id.txtInputCalories);
        inputExpirationDate = (EditText) findViewById(R.id.txtInputExpirationDate);
        inputPrice = (EditText) findViewById(R.id.txtInputPrice);
        radioTypeGroup = (RadioGroup) findViewById(R.id.radioGroupType);
        radioHot = (RadioButton) findViewById(R.id.radioHot);
        radioCold = (RadioButton) findViewById(R.id.radioCold);

        f1 = new Food();
        f1 = db.getFood(it.getLongExtra("ID",0));

        inputName.setText(f1.getName());
        inputCalories.setText(Integer.toString(f1.getCalories()));
        inputPrice.setText(Double.toString(f1.getPrice()));
        inputExpirationDate.setText(f1.getExpirationDate());

        if(f1.getType() == 10){
            radioTypeGroup.check(radioHot.getId());

        }
        else{
            radioTypeGroup.check(radioCold.getId());
        }

    }

    public void editFood(View v){

        f1.setName(inputName.getText().toString());
        f1.setCalories(Integer.parseInt(inputCalories.getText().toString()));
        f1.setExpirationDate(inputExpirationDate.getText().toString());
        f1.setPrice(Double.parseDouble(inputPrice.getText().toString()));

        int selectedId = radioTypeGroup.getCheckedRadioButtonId();
        radioTypeButton = (RadioButton) findViewById(selectedId);
        String tipoSelecionado = radioTypeButton.getText().toString();

        int tipo = 20;

        if(tipoSelecionado.equals("Quente")){
            tipo = 10;
        }

        f1.setType(tipo);

        db.updateFood(f1);
        finish();

    }

}
