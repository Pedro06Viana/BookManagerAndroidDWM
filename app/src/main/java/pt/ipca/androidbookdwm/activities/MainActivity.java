package pt.ipca.androidbookdwm.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pt.ipca.androidbookdwm.ProductManagerApp;
import pt.ipca.androidbookdwm.R;
import pt.ipca.androidbookdwm.adaptars.ProductAdapter;
import pt.ipca.androidbookdwm.interfaces.OnItemResult;
import pt.ipca.androidbookdwm.models.Product;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private RecyclerView recyclerview_cart;
    private Toolbar toolbar;
    private ProductManagerApp productManagerApp;
    private ProductAdapter adapter;
    private ProductAdapter adapter_cart;
    private Product product;
    private TextView lbl_total;
    private TextView lbl_total_cart;
    private TextView preco, preco_cart;

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
            public void onItemSelected(int position) {
                product = productManagerApp.getListaDeCompras().GetProductList1().get(position);
                product.setCb_product_add(true);
                productManagerApp.getListaDeCompras().RemoveProduct(position);
                productManagerApp.getListaDeCompras().AddProductToShoppingCart(product);

                //ACTUALIZAÇÃO DAS LABEL DE PRODUTOS NO FOOTER
                lbl_total.setText(""+ productManagerApp.getListaDeCompras().GetProductList1().size());
                lbl_total_cart.setText(""+ productManagerApp.getListaDeCompras().GetProductList2().size());
                preco.setText(productManagerApp.getListaDeCompras().GetTotalPrice() + "€");
                preco_cart.setText(productManagerApp.getListaDeCompras().GetTotalPriceOnCart() + "€");

                //INFORMAR PARA AS LISTAS SE ACTUALIZAREM
                adapter.notifyDataSetChanged();
                adapter.notifyItemRemoved(position);
                adapter_cart.notifyDataSetChanged();
                adapter_cart.notifyItemRemoved(position);
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
            public void onItemSelected(int position) {
                product = productManagerApp.getListaDeCompras().GetProductList2().get(position);
                product.setCb_product_add(false);
                productManagerApp.getListaDeCompras().RemoveProductToShoppingCart(position);
                productManagerApp.getListaDeCompras().AddProduct(product);

                //ACTUALIZAÇÃO DAS LABEL DE PRODUTOS NO FOOTER
                lbl_total.setText(""+ productManagerApp.getListaDeCompras().GetProductList1().size());
                lbl_total_cart.setText(""+ productManagerApp.getListaDeCompras().GetProductList2().size());
                preco.setText(productManagerApp.getListaDeCompras().GetTotalPrice() + "€");
                preco_cart.setText(productManagerApp.getListaDeCompras().GetTotalPriceOnCart() + "€");

                //INFORMAR PARA AS LISTAS SE ACTUALIZAREM
                adapter.notifyDataSetChanged();
                adapter.notifyItemRemoved(position);
                adapter_cart.notifyDataSetChanged();
                adapter_cart.notifyItemRemoved(position);
            }
        });
        recyclerview_cart.setAdapter(adapter_cart);
        adapter_cart.notifyDataSetChanged();

        //TOTAL PRODUTOS
        lbl_total = (TextView) findViewById(R.id.total_Prod);
        lbl_total.setText(""+ productManagerApp.getListaDeCompras().GetProductList1().size());

        //TOTAL PRODUTOS CARRINHO
        lbl_total_cart = (TextView) findViewById(R.id.total_prod_cart);
        lbl_total_cart.setText(""+productManagerApp.getListaDeCompras().GetProductList2().size());

        //PRECO TOTAL PRODUTOS
        preco  = (TextView) findViewById(R.id.total_preco);
        preco.setText(productManagerApp.getListaDeCompras().GetTotalPrice() + "€");
        preco_cart  = (TextView) findViewById(R.id.total_preco_cart);
        preco_cart.setText(productManagerApp.getListaDeCompras().GetTotalPriceOnCart() + "€");

        //Criar um novo produto
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), CreateProduct.class);
                startActivity(intent);
            }});

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
            ProductManagerApp productManagerApp = (ProductManagerApp) getApplication();
            int position = productManagerApp.getProductsize() + 1;
            int position_cart = productManagerApp.getProductCartsize() + 1;
            adapter.notifyDataSetChanged();
            adapter.notifyItemInserted(position);
            adapter_cart.notifyDataSetChanged();
            adapter_cart.notifyItemInserted(position_cart);
        }
        else{
            //adapter.notifyDataSetChanged();
            //adapter.notifyItemInserted(3);;
        }
    }
}