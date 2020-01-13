package pt.ipca.androidbookdwm.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import pt.ipca.androidbookdwm.ProductManagerApp;
import pt.ipca.androidbookdwm.R;
import pt.ipca.androidbookdwm.adaptars.ProductAdapter;
import pt.ipca.androidbookdwm.interfaces.OnItemResult;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private RecyclerView recyclerview_cart;
    private Toolbar toolbar;
    private ProductManagerApp productManagerApp;
    private ProductAdapter adapter;
    private ProductAdapter adapter_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //define o layout a utilizar
        setContentView(R.layout.activity_main);
        //import do layout toolbar.xml
        toolbar = findViewById(R.id.toolbar);
        setUpToolbar();

        productManagerApp = ((ProductManagerApp) getApplication());
        recyclerview = findViewById(R.id.product_recycler_view);

        //DEFINIR O TIPO DE RECYCLER VIEW, NESTE CASO VERTICAL
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManager);

        adapter = new ProductAdapter(productManagerApp.getListaDeCompras().GetProductList1(), new OnItemResult() {
            @Override
            public void onItemDeleted(int position) {
                //productManagerApp.removeProduct(position);
                //adapter.notifyDataSetChanged(); atualiza a lista toda
                adapter.notifyItemRemoved(position); //atualiza só na posição removida
            }
        });
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //CARRINHO DE COMPRAS
        recyclerview_cart = findViewById(R.id.product_cart_recycler_view);
        RecyclerView.LayoutManager layoutManager_cart = new LinearLayoutManager(getApplicationContext());
        recyclerview_cart.setLayoutManager(layoutManager_cart);

        adapter_cart = new ProductAdapter(productManagerApp.getListaDeCompras().GetProductList2(), new OnItemResult() {
            @Override
            public void onItemDeleted(int position) {
                //productManagerApp.removeProduct(position);
                //adapter.notifyDataSetChanged(); atualiza a lista toda
                adapter_cart.notifyItemRemoved(position); //atualiza só na posição removida
            }
        });
        recyclerview_cart.setAdapter(adapter_cart);
        adapter_cart.notifyDataSetChanged();
    }

    public void setUpToolbar() {
        //define o title na toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    //sai da activity
    @Override
    protected void onPause() {
        super.onPause();
    }

    //entra da activity
    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }
}