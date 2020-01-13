package pt.ipca.androidbookdwm.models;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.IdRes;

public class Product {

    private String product_name, prodct_obs, spinner_unidade;
    private int product_quantidade, product_preco;
    private boolean cb_product_add;
    private Categoria categoria;

    public Product(String product_name, Categoria categoria, String prodct_obs, String spinner_unidade, int product_quantidade, int product_preco, boolean cb_product_add) {
        this.product_name = product_name;
        this.categoria = categoria;
        this.prodct_obs = prodct_obs;
        this.spinner_unidade = spinner_unidade;
        this.product_quantidade = product_quantidade;
        this.product_preco = product_preco;
        this.cb_product_add = cb_product_add;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getProdct_obs() {
        return prodct_obs;
    }

    public void setProdct_obs(String prodct_obs) {
        this.prodct_obs = prodct_obs;
    }

    public String getSpinner_unidade() {
        return spinner_unidade;
    }

    public void setSpinner_unidade(String spinner_unidade) {
        this.spinner_unidade = spinner_unidade;
    }

    public int getProduct_quantidade() {
        return product_quantidade;
    }

    public void setProduct_quantidade(int product_quantidade) {
        this.product_quantidade = product_quantidade;
    }

    public int getProduct_preco() {
        return product_preco;
    }

    public void setProduct_preco(int product_preco) {
        this.product_preco = product_preco;
    }

    public boolean isCb_product_add() {
        return cb_product_add;
    }

    public void setCb_product_add(boolean cb_product_add) {
        this.cb_product_add = cb_product_add;
    }
}
