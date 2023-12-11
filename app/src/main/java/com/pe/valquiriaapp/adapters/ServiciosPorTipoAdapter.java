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
import com.google.android.material.card.MaterialCardView;
import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.model.Servicio;
import com.pe.valquiriaapp.ui.servicios_por_tipo.ServiciosPorTipoFragmentDirections;

import java.util.List;

public class ServiciosPorTipoAdapter extends RecyclerView.Adapter<ServiciosPorTipoAdapter.ViewHolder> {

    private final List<Servicio> servicioList;


    public ServiciosPorTipoAdapter(List<Servicio> servicioList) {
        this.servicioList = servicioList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicio_por_tipo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Servicio servicio = servicioList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(servicio.getImagen()[0])
                .placeholder(R.drawable.loading_gift)
                .error("https://images.vexels.com/media/users/3/152599/isolated/preview/3919975f3dac8628047ecb23632a9841-signo-de-interrogaci--n-s--mbolo-vector-by-vexels.png")
                .into(holder.imageViewServicioImg);
        holder.textViewServicioName.setText(servicio.getDescripcion());
        holder.textViewServicioPrecio.setText(Float.toString(servicio.getPrecio()));
        holder.materialCardView.setOnClickListener(view -> {
            NavDirections navDirections = ServiciosPorTipoFragmentDirections.
                    actionServiciosPorTipoFragmentToServicioDetailsFragment(servicio.getId());
            Navigation.findNavController(view).navigate(navDirections);
        });

    }

    @Override
    public int getItemCount() {
        return servicioList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewServicioImg;
        TextView textViewServicioName;
        TextView textViewServicioPrecio;
        MaterialCardView materialCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewServicioImg = itemView.findViewById(R.id.item_servicio_por_tipo_img);
            textViewServicioName = itemView.findViewById(R.id.item_servicio_por_tipo_name);
            textViewServicioPrecio = itemView.findViewById(R.id.item_servicio_por_tipo_price);
            materialCardView = itemView.findViewById(R.id.item_servicio_por_tipo_card);
        }
    }
}
