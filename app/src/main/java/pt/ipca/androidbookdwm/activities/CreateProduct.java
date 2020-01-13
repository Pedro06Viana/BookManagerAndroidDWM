package pt.ipca.androidbookdwm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;


import pt.ipca.androidbookdwm.ProductManagerApp;
import pt.ipca.androidbookdwm.R;
import pt.ipca.androidbookdwm.models.Categoria;
import pt.ipca.androidbookdwm.models.Product;

public class CreateProduct extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText product_name, product_quantidade, product_preco, prodct_obs;
    private Spinner spinner_unidade , spinner_categoria;
    private CheckBox cb_product_add;
    private ProductManagerApp productManagerApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        toolbar = findViewById(R.id.toolbar);
        setUpToolbar();
        product_name = findViewById(R.id.product_name);
        product_quantidade = findViewById(R.id.product_quantidade);
        product_preco = findViewById(R.id.product_preco);
        prodct_obs = findViewById(R.id.prodct_obs);
        spinner_unidade = findViewById(R.id.spinner_unidade);
        spinner_categoria = findViewById(R.id.spinner_categoria);
        cb_product_add = findViewById(R.id.cb_product_add);
        productManagerApp = ((ProductManagerApp) getApplication());

        Spinner spinner = (Spinner) findViewById(R.id.spinner_unidade);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_spinner = ArrayAdapter.createFromResource(this, R.array.unidade_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter_spinner);

    }

    //PARA RECEBER QUALQUER ITEM DA TOOLBAR "onOptionsItemSelected"
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish(); elemina o ecrã, volta a tras.
                onBackPressed(); //função já pre-definida que serve para voltar a tras.
                return true;
            default:
                    return super.onOptionsItemSelected(item);
        }

    }

    void setUpToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void adicionar_produto(View view) {

        Product product = new Product(product_name.toString(), new Categoria(spinner_categoria.getItemAtPosition(spinner_categoria.getSelectedItemPosition()).toString()), prodct_obs.toString(), spinner_unidade.getItemAtPosition(spinner_unidade.getSelectedItemPosition()).toString(), Integer.parseInt(product_quantidade.toString()), Integer.parseInt(product_preco.toString()), Boolean.parseBoolean(cb_product_add.toString()));
        productManagerApp.addProduct(product);
        finish();
    }
}
