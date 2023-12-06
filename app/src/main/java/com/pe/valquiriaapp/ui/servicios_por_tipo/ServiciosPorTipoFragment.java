package com.pe.valquiriaapp.ui.servicios_por_tipo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pe.valquiriaapp.R;

public class ServiciosPorTipoFragment extends Fragment {

    private ServiciosPorTipoViewModel mViewModel;

    public static ServiciosPorTipoFragment newInstance() {
        return new ServiciosPorTipoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_servicios_por_tipo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ServiciosPorTipoViewModel.class);
        // TODO: Use the ViewModel
    }

}