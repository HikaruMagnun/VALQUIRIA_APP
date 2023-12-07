package com.pe.valquiriaapp.ui.habitacion_espera;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pe.valquiriaapp.R;

public class HabitacionEsperaFragment extends Fragment {

    private HabitacionEsperaViewModel mViewModel;

    public static HabitacionEsperaFragment newInstance() {
        return new HabitacionEsperaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_habitacion_espera, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HabitacionEsperaViewModel.class);
        // TODO: Use the ViewModel
    }

}