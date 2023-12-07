package com.pe.valquiriaapp.ui.habitacion;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.pe.valquiriaapp.model.Alojamiento;
import com.pe.valquiriaapp.repository.local.AlojamientoLocalRepository;

public class HabitacionViewModel extends AndroidViewModel {

    AlojamientoLocalRepository alojamientoLocalRepository;
    public HabitacionViewModel(@NonNull Application application) {
        super(application);
        this.alojamientoLocalRepository = new AlojamientoLocalRepository(application);
    }


    public int getCodigoHabitacion() {
        Alojamiento alojamiento = alojamientoLocalRepository.obtenerDatosAlojamiento();
        return alojamiento.getHabitacion().getCodigoHabitacion();
    }
}