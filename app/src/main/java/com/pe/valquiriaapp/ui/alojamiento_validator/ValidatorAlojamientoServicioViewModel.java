package com.pe.valquiriaapp.ui.alojamiento_validator;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.pe.valquiriaapp.model.Alojamiento;
import com.pe.valquiriaapp.repository.local.AlojamientoLocalRepository;
import com.pe.valquiriaapp.repository.local.ClienteLocalRepository;
import com.pe.valquiriaapp.repository.remote.AlojamientoRemoteRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ValidatorAlojamientoServicioViewModel extends AndroidViewModel {

    AlojamientoLocalRepository alojamientoLocalRepository;
    ClienteLocalRepository clienteLocalRepository;
    AlojamientoRemoteRepository alojamientoRemoteRepository;
    //1=sin alojamientp
    //2=alojamiento pendiente  PENDIENTE
    //3= actualmente alojado   ALOJADO
    MutableLiveData<Integer> integerMutableLiveData = new MutableLiveData<>(0);

    public ValidatorAlojamientoServicioViewModel(@NonNull Application application) {
        super(application);
        alojamientoLocalRepository = new AlojamientoLocalRepository(application);
        clienteLocalRepository = new ClienteLocalRepository(application);
    }

    public void validarAlojamiento() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            if (alojamientoLocalRepository.existeInformacionAlojamiento()) {
                alojamientoLocalRepository.borrarDatosAlojamiento();
            }
            obtenerAlojamientoRemoto();

        });


    }


    private void obtenerAlojamientoRemoto() {
        alojamientoRemoteRepository = new AlojamientoRemoteRepository();
        int dni = clienteLocalRepository.obtenerDatosCliente().getDni();

        Alojamiento alojamientoPendiente = alojamientoRemoteRepository.alojamientoPorDni(dni, "PENDIENTE");
        if (alojamientoPendiente != null) {
            alojamientoLocalRepository.guardarDatosAlojamiento(alojamientoPendiente);
            integerMutableLiveData.postValue(2);
            return;
        }

        Alojamiento alojamientoAlojado = alojamientoRemoteRepository.alojamientoPorDni(dni, "ALOJADO");
        if (alojamientoAlojado != null) {
            alojamientoLocalRepository.guardarDatosAlojamiento(alojamientoAlojado);
            integerMutableLiveData.postValue(3);
            return;
        }
        integerMutableLiveData.postValue(1);
    }

    public MutableLiveData<Integer> getIntegerMutableLiveData() {
        return integerMutableLiveData;
    }

    // TODO: Implement the ViewModel
}