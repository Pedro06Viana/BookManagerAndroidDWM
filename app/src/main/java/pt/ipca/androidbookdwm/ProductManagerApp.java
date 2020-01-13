package pt.ipca.androidbookdwm;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import pt.ipca.androidbookdwm.models.Categoria;
import pt.ipca.androidbookdwm.models.Product;
import pt.ipca.androidbookdwm.models.ShoppingList;

public class ProductManagerApp extends Application {

    private ShoppingList listaDeCompras;

    //OVERRIDE
    @Override
    public void onCreate() {
        super.onCreate();

        Categoria categoria1 = new Categoria("Fruta");
        Categoria categoria2 = new Categoria("Bebidas");
        Categoria categoria3 = new Categoria("Secos");

        Product product1 = new Product("Banana", categoria1, "da Madeira", "un", 1, 1, true);
        Product product2 = new Product("Coca-cola", categoria2, "Normal", "un", 1, 1, true);
        Product product3 = new Product("Arroz", categoria3, "Agulha", "un", 1, 1, true);

        listaDeCompras = new ShoppingList("Lista do João");
        listaDeCompras.AddProduct(product1);
        listaDeCompras.AddProduct(product2);
        listaDeCompras.AddProductToShoppingCart(product3);
    }

    public ShoppingList getListaDeCompras() {
        return listaDeCompras;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
