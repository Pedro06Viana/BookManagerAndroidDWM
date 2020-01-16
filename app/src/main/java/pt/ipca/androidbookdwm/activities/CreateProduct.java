package pt.ipca.androidbookdwm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pt.ipca.androidbookdwm.ProductManagerApp;
import pt.ipca.androidbookdwm.activities.MainActivity;
import pt.ipca.androidbookdwm.R;
import pt.ipca.androidbookdwm.adaptars.ProductAdapter;
import pt.ipca.androidbookdwm.models.Categoria;
import pt.ipca.androidbookdwm.models.Product;
import pt.ipca.androidbookdwm.models.ShoppingList;

public class CreateProduct extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText product_name, product_quantidade, product_preco, prodct_obs;
    private Spinner spinner_unidade , spinner_categoria;
    private CheckBox cb_product_add;
    private ProductManagerApp productManagerApp;
    private Button button;
    private Button buttoncat;

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

        //********************//
        //SPINER UNITY********//
        //*******************//
        Spinner spinner = (Spinner) findViewById(R.id.spinner_unidade);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_spinner = ArrayAdapter.createFromResource(this, R.array.unidade_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter_spinner);

        //********************//
        //SPINER CATEGORIA***//
        //*******************//

        ProductManagerApp productManagerApp = (ProductManagerApp) getApplication();

        List<String> lstCategorias = productManagerApp.getListaDeCategorias();

        Spinner spinnercat = (Spinner) findViewById(R.id.spinner_categoria);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lstCategorias);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnercat.setAdapter(dataAdapter);

        button = (Button) findViewById(R.id.button_create);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar_produto();
            }
        });

        //Criar um nova categoria
        buttoncat = findViewById(R.id.button_createcategoria);
        buttoncat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), CreateCategoria.class);
                startActivity(intent);
            }});}

    @Override
    protected void onResume() {
        super.onResume();
        //Quando entra de novo nesta activitie refaz o spiner da categoria
        final Spinner spinnercat = (Spinner) findViewById(R.id.spinner_categoria);

        List<String> lstCategorias = productManagerApp.getListaDeCategorias();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lstCategorias);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnercat.setAdapter(dataAdapter);
    }

    //PARA RECEBER QUALQUER ITEM DA TOOLBAR "onOptionsItemSelected"
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
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

    public void adicionar_produto() {

        //TOAST DINAMICO
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        Product product;

        String name = product_name.getText().toString();
        String categoria = spinner_categoria.getItemAtPosition(spinner_categoria.getSelectedItemPosition()).toString();
        String obs = prodct_obs.getText().toString();
        String unidade = spinner_unidade.getItemAtPosition(spinner_unidade.getSelectedItemPosition()).toString();
        int quantidade = Integer.parseInt(product_quantidade.getText().toString());
        double preco = Double.parseDouble(product_preco.getText().toString());
        boolean produtoadd = true;
        int image = R.drawable.plus; //fica por defeito por não haver campo para inserir imagem

        if (cb_product_add.isChecked()) {
            produtoadd = true;
            product = new Product(name,new Categoria(categoria),obs, unidade, quantidade, preco, produtoadd, image);
            productManagerApp.listaDeCompras.AddProductToShoppingCart(product);
            text.setText("Produto adicionado ao Carrinho.");
            toast.show();
        }else {
            produtoadd = false;
            product = new Product(name, new Categoria(categoria), obs, unidade, quantidade, preco, produtoadd, image);
            productManagerApp.listaDeCompras.AddProduct(product);
            text.setText("Produto adicionado à Lista.");
            toast.show();
        }

        finish();
    }
}
