package com.pe.valquiriaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pe.valquiriaapp.R;

import java.util.List;

public class ImagenesCarouselAdapter extends RecyclerView.Adapter<ImagenesCarouselAdapter.ViewHolder> {

    private final List<String> stringList;



    public ImagenesCarouselAdapter(List<String> stringList){
        this.stringList = stringList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imagenes_carrusel,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(stringList.get(position)).into(holder.imageViewCarrousel);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCarrousel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCarrousel = itemView.findViewById(R.id.item__carousel_img);
        }
    }
}
