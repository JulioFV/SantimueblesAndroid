package com.itsoeh.proyectointegrador.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itsoeh.proyectointegrador.R;
import com.itsoeh.proyectointegrador.modelo.MSalas;

import java.util.ArrayList;

public class AdapterSalas extends RecyclerView.Adapter<AdapterSalas.ViewHolderSalas>{
    private ArrayList<MSalas> lista;
    private Bundle paquete;
    public AdapterSalas(ArrayList<MSalas> lista) {
        this.lista = lista;
    }
    private Context contexto;

    @NonNull
    @Override
    public AdapterSalas.ViewHolderSalas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salas,parent,false);
        contexto = parent.getContext();
        return new ViewHolderSalas(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSalas.ViewHolderSalas holder, int position) {
        MSalas objSalas = lista.get(position);
        paquete = new Bundle();//Creacion del Bundle
        holder.txtPrecio.setText(objSalas.getPrecio()+"");
        holder.txtCantidad.setText(objSalas.getCantidad_disponible()+"");
        holder.txtNombre.setText(objSalas.getNombre()+"");

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderSalas extends RecyclerView.ViewHolder {
        private TextView txtPrecio, txtCantidad,txtNombre;
        private ImageView btnCarrito;
        public ViewHolderSalas(@NonNull View itemView) {
            super(itemView);
                txtPrecio=itemView.findViewById(R.id.item_sala_precio);
                txtNombre=itemView.findViewById(R.id.item_sala_nom);
                txtCantidad=itemView.findViewById(R.id.item_sala_cantidad);
                btnCarrito=itemView.findViewById(R.id.item_sala_btncarrito);

        }
    }
}
