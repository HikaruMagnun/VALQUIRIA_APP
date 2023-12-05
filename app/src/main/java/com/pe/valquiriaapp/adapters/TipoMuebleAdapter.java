package com.pe.valquiriaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.model.MueblesTipo;
import com.pe.valquiriaapp.ui.reserva_details.ReservaDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class TipoMuebleAdapter extends RecyclerView.Adapter<TipoMuebleAdapter.ViewHolder> {

    private final List<MueblesTipo> mueblesTipoList;
    ReservaDetailsViewModel reservaDetailsViewModel;


    public TipoMuebleAdapter(List<MueblesTipo> mueblesTipoList, ReservaDetailsViewModel reservaDetailsViewModel) {
        this.mueblesTipoList = mueblesTipoList;
        this.reservaDetailsViewModel = reservaDetailsViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habitacion_personalizacion_tipo_mueble,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MueblesTipo mueblesTipo = mueblesTipoList.get(position);
        holder.circularProgressIndicatorTipoMuebleLoading.setVisibility(View.GONE);
        holder.recyclerViewTipoMuebleMuebles.setVisibility(View.GONE);
        Glide.with(holder.itemView.getContext())
                .load(mueblesTipo.getImagen())
                .placeholder(R.drawable.loading_gift)
                .error("https://images.vexels.com/media/users/3/152599/isolated/preview/3919975f3dac8628047ecb23632a9841-signo-de-interrogaci--n-s--mbolo-vector-by-vexels.png")
                .into(holder.imageViewTipoMuebleImage);
        holder.textViewTipoMuebleName.setText(mueblesTipo.getTipo());
        MueblesAdapter mueblesAdapter = new MueblesAdapter(
                mueblesTipo.getMuebleList(),reservaDetailsViewModel
        );
        holder.recyclerViewTipoMuebleMuebles.setAdapter(mueblesAdapter);

        holder.textViewTipoMuebleButoon.addOnCheckedChangeListener(new MaterialButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MaterialButton button, boolean isChecked) {
                if (isChecked){
                    holder.recyclerViewTipoMuebleMuebles.setVisibility(View.VISIBLE);

                }else {
                    holder.recyclerViewTipoMuebleMuebles.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mueblesTipoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewTipoMuebleImage;
        TextView textViewTipoMuebleName;
        MaterialButton textViewTipoMuebleButoon;
        CircularProgressIndicator circularProgressIndicatorTipoMuebleLoading;
        RecyclerView recyclerViewTipoMuebleMuebles;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewTipoMuebleImage = itemView.findViewById(R.id.tipo_mueble_imagen);
            textViewTipoMuebleName = itemView.findViewById(R.id.tipo_mueble_name);
            textViewTipoMuebleButoon = itemView.findViewById(R.id.tipo_mueble_expand_button);
            circularProgressIndicatorTipoMuebleLoading = itemView.findViewById(R.id.tipo_mueble_loading);
            recyclerViewTipoMuebleMuebles = itemView.findViewById(R.id.tipo_mueble_recycler);
        }
    }
}
