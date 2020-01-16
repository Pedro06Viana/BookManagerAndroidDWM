package pt.ipca.androidbookdwm.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import pt.ipca.androidbookdwm.ProductManagerApp;
import pt.ipca.androidbookdwm.R;
import pt.ipca.androidbookdwm.models.Categoria;
import pt.ipca.androidbookdwm.models.Product;

public class CreateCategoria extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText categorianame;
    private Spinner spinner_unidade , spinner_categoria;
    private ProductManagerApp productManagerApp;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_categoria);
        toolbar = findViewById(R.id.toolbar);
        setUpToolbar();

        categorianame = findViewById(R.id.categoria_name);

        productManagerApp = ((ProductManagerApp) getApplication());

        ProductManagerApp productManagerApp = (ProductManagerApp) getApplication();

        List<String> lstCategorias = productManagerApp.getListaDeCategorias();

        button = (Button) findViewById(R.id.button_create_categoria);
        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                adicionar_categoria();   }
        });
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

    public void adicionar_categoria() {

        String name = categorianame.getText().toString();

        Categoria categoria1 = new Categoria(name);
        productManagerApp.addCategoria(categoria1);

        finish();
    }
}
