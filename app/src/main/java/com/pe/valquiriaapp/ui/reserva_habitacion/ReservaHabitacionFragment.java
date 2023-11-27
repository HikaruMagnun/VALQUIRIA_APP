package com.pe.valquiriaapp.ui.reserva_habitacion;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.adapters.HabitacionAdapter;
import com.pe.valquiriaapp.model.Habitacion;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

public class ReservaHabitacionFragment extends Fragment {

    private ReservaHabitacionViewModel mViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //declaraciones
        View view = inflater.inflate(R.layout.fragment_reserva_habitacion, container, false);
        RecyclerView recyclerViewListaHabtaciones = view.findViewById(R.id.reserva_habitacion_lista_habitacion);
        ProgressBar progressBar = view.findViewById(R.id.reserva_habitacion_loading);

        //configurar
        LocalDate inicioDate = longADate(ReservaHabitacionFragmentArgs.fromBundle(getArguments()).getStarDate());
        LocalDate finDate = longADate(ReservaHabitacionFragmentArgs.fromBundle(getArguments()).getEndDate());
        int dias = Period.between(inicioDate,finDate).getDays() + 1;
        mViewModel = new ViewModelProvider(this).get(ReservaHabitacionViewModel.class);
        mViewModel.pasarDatos(inicioDate,finDate);
        recyclerViewListaHabtaciones.setVisibility(View.GONE);
        recyclerViewListaHabtaciones.setLayoutManager(new LinearLayoutManager(requireContext()));
        progressBar.setVisibility(View.VISIBLE);

        //observer
        mViewModel.getListHabitacionMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Habitacion>>() {
            @Override
            public void onChanged(List<Habitacion> habitaciones) {
                HabitacionAdapter habitacionAdapter = new HabitacionAdapter(habitaciones,
                        inicioDate.toString(),
                        finDate.toString(),
                        dias);
                System.out.println(dias);
                recyclerViewListaHabtaciones.setAdapter(habitacionAdapter);
                recyclerViewListaHabtaciones.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });

        return view;
    }

    private LocalDate longADate(Long dateLong){
        Instant instant = Instant.ofEpochMilli(dateLong);
        return instant.atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
    }


}