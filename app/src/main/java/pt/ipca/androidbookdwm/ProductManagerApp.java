package pt.ipca.androidbookdwm;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import pt.ipca.androidbookdwm.models.Categoria;
import pt.ipca.androidbookdwm.models.Product;
import pt.ipca.androidbookdwm.models.ShoppingList;

public class ProductManagerApp extends Application {

    public ShoppingList listaDeCompras;
    private List<Categoria> ListCategorias = new ArrayList<>();

    //OVERRIDE
    @Override
    public void onCreate() {
        super.onCreate();

        Categoria categoria1 = new Categoria("Fruta");
        Categoria categoria2 = new Categoria("Bebidas");
        Categoria categoria3 = new Categoria("Secos");

        Product product1 = new Product("Banana", categoria1, "da Madeira", "un", 1, 1, false, R.drawable.banana);
        Product product2 = new Product("Coca-cola", categoria2, "Normal", "un", 1, 1, false, R.drawable.coca);
        Product product3 = new Product("Arroz", categoria3, "Agulha", "un", 1, 1, true, R.drawable.arroz);

        listaDeCompras = new ShoppingList("Lista do João");
        listaDeCompras.AddProduct(product1);
        listaDeCompras.AddProduct(product2);
        listaDeCompras.AddProductToShoppingCart(product3);

        ListCategorias.add(categoria1);
        ListCategorias.add(categoria2);
        ListCategorias.add(categoria3);
    }

    public ShoppingList getListaDeCompras() {
        return listaDeCompras;
    }

    public List<String> getListaDeCategorias() {
        List<String> lstCategoriasString = new ArrayList<>();
        for (Categoria cat: ListCategorias) {
            lstCategoriasString.add(cat.getName());
        }
        return lstCategoriasString;
    }

    public void addCategoria(Categoria categoria)
    {
        ListCategorias.add(categoria);
    }
    public void addProduct(Product product)
    {
        listaDeCompras.AddProduct(product);
    }
    public void AddProductToShoppingCart(Product product)
    {
        listaDeCompras.AddProductToShoppingCart(product);
    }
    public int getProductsize(){{
        return listaDeCompras.GetProductList1().size();}
    }
    public int getProductCartsize(){{
        return listaDeCompras.GetProductList2().size();}
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}


