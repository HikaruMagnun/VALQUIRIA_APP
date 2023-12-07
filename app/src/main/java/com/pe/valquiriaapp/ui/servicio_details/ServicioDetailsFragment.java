package com.pe.valquiriaapp.ui.servicio_details;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pe.valquiriaapp.R;

public class ServicioDetailsFragment extends Fragment {

    private ServicioDetailsViewModel mViewModel;

    public static ServicioDetailsFragment newInstance() {
        return new ServicioDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servicio_details, container, false);



        return view;
    }



}