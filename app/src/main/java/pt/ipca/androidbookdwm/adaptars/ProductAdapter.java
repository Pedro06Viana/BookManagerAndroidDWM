package pt.ipca.androidbookdwm.adaptars;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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

        holder.getProduct_name().setText(product.getProduct_name() + " - " + product.getCategoria().getName());
        holder.getProduct_preco().setText(product.getProduct_preco() + "€");
        holder.getProduct_discription().setText(product.getProduct_quantidade() + " " + product.getSpinner_unidade() + " - ");
        holder.getImage().setImageResource(product.getImage());
        holder.getCb_product_add().setChecked(product.isCb_product_add());

        //EVENTO ADICIONAR AO CARRINHO função onItemSelected(position) no MAIN_ACTIVITY
        holder.getCb_product_add().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemResult != null)
                {
                    onItemResult.onItemSelected(position);
                }
            }
        });

    }

    @Override //RETORNA O TAMANHO DAS LISTAS
    public int getItemCount() {
        return lstProducts != null ? lstProducts.size() : 0; //if ... else ...
    }

    //Permite preencher a recycler view layout com os dados das listas
    //CONSTRUTOR DA RECYCLER-VIEW
    public class BookViewHolder extends RecyclerView.ViewHolder {

        private TextView product_name, product_preco, product_discription;
        private CheckBox cb_product_add;
        private ImageView image;

        //O QUE VAI BUSCAR A VIEW
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.product_item_title);
            product_preco = itemView.findViewById(R.id.product_item_preco);
            product_discription = itemView.findViewById(R.id.product_item_discription);
            cb_product_add = itemView.findViewById(R.id.check_if_select);
            image = (ImageView)itemView.findViewById(R.id.imageView);
        }

        public TextView getProduct_name() {
            return product_name;
        }

        public void setProduct_name(TextView product_name) {
            this.product_name = product_name;
        }

        public TextView getProduct_preco() {
            return product_preco;
        }

        public void setProduct_preco(TextView product_preco) {
            this.product_preco = product_preco;
        }

        public CheckBox getCb_product_add() {
            return cb_product_add;
        }

        public void setCb_product_add(CheckBox cb_product_add) {
            this.cb_product_add = cb_product_add;
        }

        public TextView getProduct_discription() {
            return product_discription;
        }

        public void setProduct_discription(TextView product_discription) {
            this.product_discription = product_discription;
        }

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }
    }

}
