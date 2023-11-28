package com.pe.valquiriaapp.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.model.Habitacion;
import com.pe.valquiriaapp.ui.reserva_habitacion.ReservaHabitacionFragmentArgs;
import com.pe.valquiriaapp.ui.reserva_habitacion.ReservaHabitacionFragmentDirections;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HabitacionAdapter extends RecyclerView.Adapter<HabitacionAdapter.ViewHolder> {

    private final List<Habitacion> listaHabitacion;
    private Bundle arguments;

    String inicioDate;
    String finDate;
    int totalDays;


    public HabitacionAdapter(List<Habitacion> listaHabitacion,String inicioDate,String finDate,int totalDays){
        this.listaHabitacion = listaHabitacion;
        this.inicioDate = inicioDate;
        this.finDate = finDate;
        this.totalDays =  totalDays;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habitacion,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Habitacion habitacion = listaHabitacion.get(position);
        Glide.with(holder.itemView.getContext())
                .load(habitacion.getImagenes()[0])
                .placeholder(R.drawable.loading_gift)
                .error(R.drawable.item_habitacion_noload)
                .into(holder.imageViewHabitacionIamge);
        holder.textViewHabitacionTipo.setText(habitacion.getTipo());
        holder.textViewHabitacionDescription.setText(habitacion.getDescripcion());
        holder.textViewHabitacionPrecio.setText(String.valueOf(habitacion.getPrecioDia()));

        holder.imageButtonHabitacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections navDirections = ReservaHabitacionFragmentDirections
                        .actionNavigationReservaHabitacionToReservaDetailsFragment(
                                totalDays,
                                inicioDate,
                                finDate,
                                habitacion.getCodigoHabitacion()
                        );
                Navigation.findNavController(view)
                        .navigate(navDirections);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaHabitacion.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewHabitacionIamge;
        TextView textViewHabitacionTipo;
        TextView textViewHabitacionDescription;
        TextView textViewHabitacionPrecio;
        ImageButton imageButtonHabitacionButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewHabitacionIamge = itemView.findViewById(R.id.item_habitacion_img);
            textViewHabitacionTipo = itemView.findViewById(R.id.item_habitacion_tipo);
            textViewHabitacionDescription = itemView.findViewById(R.id.item_habitacion_description);
            textViewHabitacionPrecio = itemView.findViewById(R.id.item_habitacion_precio);
            imageButtonHabitacionButton = itemView.findViewById(R.id.item_habitacion_button);
        }
    }
}
