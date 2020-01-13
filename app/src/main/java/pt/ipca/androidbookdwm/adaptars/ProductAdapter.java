package pt.ipca.androidbookdwm.adaptars;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pt.ipca.androidbookdwm.ProductManagerApp;
import pt.ipca.androidbookdwm.R;
import pt.ipca.androidbookdwm.interfaces.OnItemResult;
import pt.ipca.androidbookdwm.models.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.BookViewHolder> {

    private OnItemResult onItemResult;
    private List<Product> lstProducts = new ArrayList<>();

    public ProductAdapter(List<Product> lstProducts, OnItemResult onItemResult) {
        this.lstProducts = lstProducts;
        this.onItemResult = onItemResult;
    }

    @NonNull
    @Override //É AQUI QUE VAMOS DEFINIR O LAYOUT
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Adiciona um layout existente a uma variavel
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent,false);

        return new BookViewHolder(itemview);
    }

    //É CHAMADA O NUMERO DE VEZES DO TAMANHO DO ARRAY
    //CADA VEZ QUE É CHAMADA CRIA UMA VIEW COM OS DADOS ASSOCIADOS
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, final int position) {
        Product product = lstProducts.get(position);

        holder.getProduct_name().setText(product.getProduct_name());
        holder.getProduct_quantidade().setText("" + product.getProduct_quantidade());
        holder.getCb_product_add().setChecked(product.isCb_product_add());

        /*//Cria o evento click, quando clicar no botão chama a função onItemDeleted(position);
        holder.getBtnDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemResult != null)
                {
                    onItemResult.onItemDeleted(position);
                }
            }
        });*/

    }

    @Override //RETORNA O TAMANHO DAS LISTAS
    public int getItemCount() {
        return lstProducts != null ? lstProducts.size() : 0; //if ... else ...
    }

    //Permite preencher a recycler view layout com os dados das listas
    //CONSTRUTOR DA RECYCLER-VIEW
    public class BookViewHolder extends RecyclerView.ViewHolder {

        private TextView product_name, product_quantidade;
        private CheckBox cb_product_add;

        //O QUE VAI BUSCAR A VIEW
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.product_item_title);
            product_quantidade = itemView.findViewById(R.id.product_item_discription);
            cb_product_add = itemView.findViewById(R.id.check_if_select);
        }

        public TextView getProduct_name() {
            return product_name;
        }

        public void setProduct_name(TextView product_name) {
            this.product_name = product_name;
        }

        public TextView getProduct_quantidade() {
            return product_quantidade;
        }

        public void setProduct_quantidade(TextView product_quantidade) {
            this.product_quantidade = product_quantidade;
        }

        public CheckBox getCb_product_add() {
            return cb_product_add;
        }

        public void setCb_product_add(CheckBox cb_product_add) {
            this.cb_product_add = cb_product_add;
        }
    }

}
