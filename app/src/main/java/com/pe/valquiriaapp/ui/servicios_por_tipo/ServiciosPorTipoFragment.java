package com.pe.valquiriaapp.ui.servicios_por_tipo;

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
import com.pe.valquiriaapp.adapters.ServiciosPorTipoAdapter;

public class ServiciosPorTipoFragment extends Fragment {

    private ServiciosPorTipoViewModel mViewModel;

    public static ServiciosPorTipoFragment newInstance() {
        return new ServiciosPorTipoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servicios_por_tipo, container, false);
        mViewModel = new ViewModelProvider(this).get(ServiciosPorTipoViewModel.class);
        RecyclerView recyclerViewServiciosPorTipo = view.findViewById(R.id.fragment_servicio_por_tipo_recycler);
        CircularProgressIndicator circularProgressIndicator = view.findViewById(R.id.fragment_servicio_por_tipo_loading);
        String tipo = ServiciosPorTipoFragmentArgs.fromBundle(getArguments()).getTipoServicio();

        recyclerViewServiciosPorTipo.setVisibility(View.GONE);
        circularProgressIndicator.setVisibility(View.VISIBLE);

        mViewModel.cargarServicioPorTipo(tipo);

        mViewModel.getListServicioMutableLiveData().observe(getViewLifecycleOwner(),servicios -> {
            ServiciosPorTipoAdapter serviciosPorTipoAdapter = new ServiciosPorTipoAdapter(servicios);
            recyclerViewServiciosPorTipo.setAdapter(serviciosPorTipoAdapter);
            recyclerViewServiciosPorTipo.setVisibility(View.VISIBLE);
            circularProgressIndicator.setVisibility(View.GONE);
        });

        return view;
    }



}