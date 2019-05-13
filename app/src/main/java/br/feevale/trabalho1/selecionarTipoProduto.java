package br.feevale.trabalho1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class selecionarTipoProduto extends AppCompatActivity {

    public static final int CODE_SELECT_PRODUCT_TYPE_ACTIVITY = 1998;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_tipo_produto);
    }

    public void selectBtnFood(View v){
        Intent it  = new Intent(getBaseContext(), ListFood.class);
        startActivityForResult(it,CODE_SELECT_PRODUCT_TYPE_ACTIVITY);
    }

    public void selectBtnDrink(View v){
        Intent it  = new Intent(getBaseContext(), ListDrink.class);
        startActivityForResult(it,CODE_SELECT_PRODUCT_TYPE_ACTIVITY);
    }
}
