package com.pe.valquiriaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.model.MueblesTipo;
import com.pe.valquiriaapp.model.ServicioTipo;
import com.pe.valquiriaapp.ui.servicios_tipos.ServicioTiposFragmentDirections;

import java.util.List;

public class TipoServicioAdapter extends RecyclerView.Adapter<TipoServicioAdapter.ViewHolder> {

    private final List<ServicioTipo> servicioTipoList;


    public TipoServicioAdapter(List<ServicioTipo> servicioTipoList) {
        this.servicioTipoList = servicioTipoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicio_tipo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServicioTipo servicioTipo = servicioTipoList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(servicioTipo.getImagen())
                .into(holder.imageViewTipoServicioImage);
        holder.textViewTipoServicioName.setText(servicioTipo.getTipo());
        holder.materialCardView.setOnClickListener(view -> {
            NavDirections navDirections = ServicioTiposFragmentDirections
                    .actionServicioTiposFragmentToServiciosPorTipoFragment(servicioTipo.getTipo());
            Navigation.findNavController(view).navigate(navDirections);

        });

    }

    @Override
    public int getItemCount() {
        return servicioTipoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView materialCardView;
        ImageView imageViewTipoServicioImage;
        TextView textViewTipoServicioName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.item_servicio_tipo_card);
            imageViewTipoServicioImage = itemView.findViewById(R.id.item_servicio_tipo_imagen);
            textViewTipoServicioName = itemView.findViewById(R.id.item_servicio_tipo_text);
        }
    }
}
