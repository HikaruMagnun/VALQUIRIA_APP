package com.pe.valquiriaapp.ui.habitacion_espera;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.pe.valquiriaapp.model.Alojamiento;
import com.pe.valquiriaapp.repository.local.AlojamientoLocalRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HabitacionEsperaViewModel extends AndroidViewModel {

    AlojamientoLocalRepository alojamientoLocalRepository;

    MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();
    public HabitacionEsperaViewModel(@NonNull Application application) {
        super(application);
        alojamientoLocalRepository = new AlojamientoLocalRepository(application);
    }

    public void solicitarDatos() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Alojamiento alojamiento ;
            alojamiento = alojamientoLocalRepository.obtenerDatosAlojamiento();

            String string = alojamiento.getFechaAlojamiento().toString() + " hasta\n"
                    + alojamiento.getFechaAlojamientoVencimiento().toString();
            stringMutableLiveData.postValue(string);
        });
    }

    public MutableLiveData<String> getStringMutableLiveData() {
        return stringMutableLiveData;
    }

    // TODO: Implement the ViewModel
}