package com.pe.valquiriaapp.ui.servicios_tipos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.adapters.TipoServicioAdapter;

public class ServicioTiposFragment extends Fragment {

    private ServicioTiposViewModel mViewModel;

    public static ServicioTiposFragment newInstance() {
        return new ServicioTiposFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servicio_tipos, container, false);
        mViewModel = new ViewModelProvider(this).get(ServicioTiposViewModel.class);
        CircularProgressIndicator circularProgressIndicator = view.findViewById(R.id.fragment_servicio_tipos_loading);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_servicio_tipos_recicler);
        circularProgressIndicator.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        mViewModel.consultarTipos();
        mViewModel.getListServicioTipoMutableLiveData().observe(getViewLifecycleOwner(),servicioTipos -> {
            TipoServicioAdapter tipoServicioAdapter = new TipoServicioAdapter(servicioTipos);
            recyclerView.setAdapter(tipoServicioAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            circularProgressIndicator.setVisibility(View.GONE);

        });
        return view;
    }



}