package com.pe.valquiriaapp.ui.habitacion_espera;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.pe.valquiriaapp.R;

public class HabitacionEsperaFragment extends Fragment {

    private HabitacionEsperaViewModel mViewModel;

    public static HabitacionEsperaFragment newInstance() {
        return new HabitacionEsperaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habitacion_espera, container, false);
        ImageView imageViewgif = view.findViewById(R.id.habitacion_espera_gif);
        Glide.with(view.getContext()).load(R.drawable.wait).into(imageViewgif);
        /*
        //mViewModel = new ViewModelProvider(this).get(HabitacionEsperaViewModel.class);
        CircularProgressIndicator circularProgressIndicator = view.findViewById(R.id.habitacion_espera_progressBar);

        TextView textViewFecha = view.findViewById(R.id.habitacion_espera_fecha);
        LinearLayout linearLayout = view.findViewById(R.id.habitacion_espera_lay);
        circularProgressIndicator.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);

/*
        mViewModel.solicitarDatos();
        mViewModel.getStringMutableLiveData().observe(getViewLifecycleOwner(),s -> {
            //Glide.with(view.getContext()).load(R.drawable.wait).into(imageViewgif);
            textViewFecha.setText(s);
            circularProgressIndicator.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        });

*/


        return view;
    }



}