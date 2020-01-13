package pt.ipca.androidbookdwm.models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingList {
    //FORMATAÇÃO VARIAVEL PERCENTAGEM
    DecimalFormat df = new DecimalFormat("#.##");

    /*VARIAVEIS*/
    private String name;

    /*LISTAS*/
    private List<Product> productList1 = new ArrayList<>();
    private List<Product> productList2 = new ArrayList<>();

    //CONSTRUTOR
    public ShoppingList(String name) {
        this.name = name;
    }

    //GET & SET
    public String GetListName() {
        return name;
    }
    public void SetListName(String name) {
        this.name = name;
    }
    public List<Product> GetProductList1() {
        return productList1;
    }
    public void SetProductList1(List<Product> productList1) {
        this.productList1 = productList1;
    }
    public List<Product> GetProductList2() {
        return productList2;
    }
    public void SetProductList2(List<Product> productList2) {
        this.productList2 = productList2;
    }

    //FUNÇÕES E FUNCIONALIDADES

    public String GetNameList() {
        return name;
    }

    public void AddUser(Product produto) {
        GetProductList1().add(produto);
    }
    public double GetPercentageCompleted() {

        double percentage = 0;
        double list1 = GetProductList1().size();
        double list2 = GetProductList2().size();

        percentage = ( list2 / (list1 + list2) ) * 100;

        return percentage;
    }
    public int TotalOfProducts() {
        return GetProductList1().size();
    }
    public int TotalOfProductsOnShoppingCart() {
        return GetProductList2().size();
    }
    public double GetTotalPrice() {
        double total = 0;
        double price = 0;

        for (int i = 0; i < GetProductList1().size(); i++)
        {
            price = GetProductList1().get(i).getProduct_preco();
            total = total + price;
        }

        return total;
    }
    public double GetTotalPriceOnCart() {
        double total = 0;
        double price = 0;

        for (int i = 0; i < GetProductList2().size(); i++)
        {
            price = GetProductList2().get(i).getProduct_preco();
            total = total + price;
        }

        return total;
    }
    public void AddProduct(Product produto) {
        GetProductList1().add(produto);
    }
    public void RemoveProduct(Product produto) {
        GetProductList1().remove(produto);
    }
    public void AddProductToShoppingCart(Product produto) {
        GetProductList2().add(produto);
    }
    public void RemoveProductToShoppingCart(Product produto) {
        GetProductList2().remove(produto);
    }


    /*-----------------------------------
    BLOCO PARA IMPRIMIR POR CATEGORIAS
    -----------------------------------*/

    private HashMap<String, String> ProductsByCategory = new HashMap<>(); //HasMap
    private List<Product> listaProdutosTotal = new ArrayList<>(); //Concatenação das Listas de Produtos
    private List<String> categorias = new ArrayList<>(); //Lista de String com as categorias sem estarem repetidas

    //VARIAVEIS TEMPORARIAS
    private String catTemp = "";
    private String catTemp2 = "";

    //FUNÇÕES

    public List<Product> GetListaProdutosTotal() {
        return listaProdutosTotal;
    }

    public List<String> GetListCategorias() {
        return categorias;
    }

    //FUNÇÃO PRINCIPAL QUE CHAMA OS OUTROS METODOS
    public HashMap<String, String> GetProdutoPorCategoria() {

        GetListaProdutosTotal().clear(); //Limpeza la Lista pois numa fase de testes quando de voltava a chamar esta função, a mesma duplicava os valores
        //Concatenação das Listas
        GetListaProdutosTotal().addAll(GetProductList1());
        GetListaProdutosTotal().addAll(GetProductList2());

        GetCategorias(); //Vai percorrer GetListaProdutosTotal e retirar as categorias sem repetir
        GetProdutos(); //Vai retirar os produtos por categorias
        return ProductsByCategory;
    }

    public void GetCategorias() {

        for (int i = 0; i < GetListaProdutosTotal().size(); i++)
        {
            catTemp = listaProdutosTotal.get(i).getCategoria().getName(); //recebe a categoria na posição i da listaProdutosTotal

            if (i == 0)
            {
                GetListCategorias().add(catTemp); //Serve para guardar a primeira categoria existente
            }

            for (int j = i + 1; j < GetListaProdutosTotal().size() ; j++)
            {
                catTemp2 = listaProdutosTotal.get(j).getCategoria().getName(); //recebe a categoria na posição j = i + 1

                if (!catTemp.equals(catTemp2))
                {
                    GetListCategorias().add(catTemp2); //Se forem diferentes guardamos a segunda categoria na categorias
                    break;//como não queremos voltar a guardar uma categoria repetida saltamos para fora do for (j)
                }else
                {
                    break;
                }
            }
        }
    }

    public void GetProdutos() {

        String string = ""; //variavel que vai conter os produtos

        for (int i = 0; i < GetListCategorias().size(); i++)
        {
            string = ""; //inicializada sempre que o i é incrementado, pois representa os VALUES de uma KEY
            catTemp = GetListCategorias().get(i); //recebe a categoria, mas desta vez do array de categorias, este campo tambem vai corresponder á KEY

            for (int j = 0; j < GetListaProdutosTotal().size() ; j++)
            {
                catTemp2 = listaProdutosTotal.get(j).getCategoria().getName(); //recebe a categoria da listaProdutosTotal

                if (catTemp.equals(catTemp2)) //se a categoria da lista categoria == categoria do produto na listaProdutosTotal temos o nosso VALUE
                {
                    string = string + listaProdutosTotal.get(j).getProduct_name() + " / "; //concatenamos os values
                }
            }
            ProductsByCategory.put(catTemp, string); //inserimos os valores KEY and VALUE no HASMAP
        }
    }

}
