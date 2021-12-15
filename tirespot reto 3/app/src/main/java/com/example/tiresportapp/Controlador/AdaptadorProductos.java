package com.example.tiresportapp.Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiresportapp.Casodeuso.LlantaActionCase;
import com.example.tiresportapp.Modelo.Llanta;
import com.example.tiresportapp.R;

import java.util.ArrayList;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolderProductos>{

    ArrayList<Llanta> productos;
    Context context;

    public AdaptadorProductos(ArrayList<Llanta> productos,Context context) {

        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderProductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        return new ViewHolderProductos(vista,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProductos holder, int position) {


        Llanta llanta = productos.get(position);
        holder.prodImagen.setImageResource(productos.get(position).getImagen());
        holder.produNombre.setText(productos.get(position).getNombre());
        holder.produPrecio.setText(LlantaActionCase.formatearNumero(productos.get(position).getPrecio()));
        holder.llanta = llanta;

        holder.btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Se habilitara pronto", Toast.LENGTH_SHORT).show();
            }
        });


        holder.contador = llanta.getCantidad();
        holder.btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.contador==0){
                    holder.contador+=1;
                    LlantaActionCase.setCantidad(llanta.getId(),1, context);
                    Toast.makeText(context, "Usted agrego: "+
                            llanta.getNombre(), Toast.LENGTH_SHORT).show();
                }else{
                    holder.contador+=1;
                    LlantaActionCase.setCantidad(llanta.getId(), holder.contador, context);
                    Toast.makeText(context, "Usted aumento en 1: "+
                            llanta.getNombre(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolderProductos extends RecyclerView.ViewHolder{

        ImageView prodImagen;
        TextView produNombre;
        TextView produPrecio;
        Llanta llanta;
        Button btnCarrito;
        Integer contador;
        ImageButton btnFavorito;

        public ViewHolderProductos(@NonNull View itemView,Context context) {
            super(itemView);

            prodImagen = (ImageView) itemView.findViewById(R.id.imagenProducto);
            produNombre = (TextView) itemView.findViewById(R.id.nombreProductoVie);
            produPrecio = (TextView) itemView.findViewById(R.id.precioProductoVie);
            btnCarrito = (Button) itemView.findViewById(R.id.btnCarrito);
            btnFavorito = (ImageButton) itemView.findViewById(R.id.btnFav);

        }

    }
}

