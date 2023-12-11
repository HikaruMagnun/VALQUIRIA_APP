package com.pe.valquiriaapp.ui.info_no_reserva;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.ui.servicio_no_reserva.ServicioNoReservaDirections;

public class InfoNoReserva extends Fragment {


    public static InfoNoReserva newInstance() {
        return new InfoNoReserva();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_no_reserva, container, false);
        ImageView imageViewImg = view.findViewById(R.id.info_no_reserva_img);
        MaterialButton materialButtonRedirev = view.findViewById(R.id.info_no_reserva_button);
        Glide.with(view).load(R.drawable.error_gif).into(imageViewImg);
        materialButtonRedirev.setOnClickListener(view1 -> {
            materialButtonRedirev.setEnabled(false);
            NavDirections navDirections = InfoNoReservaDirections.actionInfoNoReservaToNavigationAlojamientoValidator();
            Navigation.findNavController(view).navigate(navDirections);
        });
        return view;
    }



}