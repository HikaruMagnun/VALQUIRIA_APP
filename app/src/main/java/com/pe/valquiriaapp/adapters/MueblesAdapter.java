package com.pe.valquiriaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.model.Mueble;
import com.pe.valquiriaapp.ui.reserva_details.ReservaDetailsViewModel;

import java.util.List;

public class MueblesAdapter extends RecyclerView.Adapter<MueblesAdapter.ViewHolder> {

    private final List<Mueble> muebleList;
    ReservaDetailsViewModel reservaDetailsViewModel;


    public MueblesAdapter(List<Mueble> muebleList, ReservaDetailsViewModel reservaDetailsViewModel) {
        this.muebleList = muebleList;
        this.reservaDetailsViewModel = reservaDetailsViewModel;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habitacion_tipo_mueble_mueble,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mueble mueble = muebleList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(mueble.getImagen())
                .placeholder(R.drawable.loading_gift)
                .error("https://images.vexels.com/media/users/3/152599/isolated/preview/3919975f3dac8628047ecb23632a9841-signo-de-interrogaci--n-s--mbolo-vector-by-vexels.png")
                .into(holder.imageViewMuebleImg);
        holder.textViewMuebleName.setText(mueble.getDescription());
        holder.textViewMueblePrecio.setText(Float.toString(mueble.getPrecio()));
        holder.checkBoxMuebleCheck.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                reservaDetailsViewModel.getComentarioAlojamiento().add(
                        holder.textViewMuebleName.getText().toString()
                );
                reservaDetailsViewModel.getFloatPrecioTotalMutableLiveData().setValue(
                        reservaDetailsViewModel.getFloatPrecioTotalMutableLiveData().
                                getValue() + mueble.getPrecio()
                );

            }else {
                reservaDetailsViewModel.getComentarioAlojamiento().remove(
                        holder.textViewMuebleName.getText().toString()
                );
                reservaDetailsViewModel.getFloatPrecioTotalMutableLiveData().setValue(
                        reservaDetailsViewModel.getFloatPrecioTotalMutableLiveData().
                                getValue() - mueble.getPrecio()
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return muebleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMuebleImg;
        TextView textViewMuebleName;
        TextView textViewMueblePrecio;
        CheckBox checkBoxMuebleCheck;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMuebleImg = itemView.findViewById(R.id.mueble_img);
            textViewMuebleName = itemView.findViewById(R.id.mueble_name);
            textViewMueblePrecio = itemView.findViewById(R.id.mueble_precio);
            checkBoxMuebleCheck = itemView.findViewById(R.id.mueble_check_box);
        }
    }
}
