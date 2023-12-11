package com.pe.valquiriaapp.ui.servicio_details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pe.valquiriaapp.model.Servicio;
import com.pe.valquiriaapp.model.ServicioSolicitado;
import com.pe.valquiriaapp.repository.local.AlojamientoLocalRepository;
import com.pe.valquiriaapp.repository.local.ClienteLocalRepository;
import com.pe.valquiriaapp.repository.remote.ServicioRemoteRepository;
import com.pe.valquiriaapp.repository.remote.ServicioSolicitadoRemoteRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServicioDetailsViewModel extends AndroidViewModel {

    ServicioRemoteRepository servicioRemoteRepository;
    ServicioSolicitadoRemoteRepository servicioSolicitadoRemoteRepository;
    AlojamientoLocalRepository alojamientoLocalRepository;
    ClienteLocalRepository clienteLocalRepository;
    MutableLiveData<Servicio> servicioMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();

    public ServicioDetailsViewModel(@NonNull Application application) {
        super(application);
        alojamientoLocalRepository = new AlojamientoLocalRepository(application);
        clienteLocalRepository = new ClienteLocalRepository(application);
    }

    public void getDatos(int id) {

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            servicioRemoteRepository = new ServicioRemoteRepository();
            Servicio servicio = servicioRemoteRepository.servicioPorId(id);
            servicioMutableLiveData.postValue(
                    servicio
            );
        });
    }

    public MutableLiveData<Servicio> getServicioMutableLiveData() {
        return servicioMutableLiveData;
    }

    public void pasarServicioSolicitado(int idServicio, int cantidad) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            ServicioSolicitado servicioSolicitado = new ServicioSolicitado();
            servicioSolicitado.getServicio().setId(idServicio);
            servicioSolicitado.setAlojamiento(alojamientoLocalRepository.obtenerDatosAlojamiento());
            servicioSolicitado.setCantidad(cantidad);
            servicioSolicitadoRemoteRepository = new ServicioSolicitadoRemoteRepository();
            booleanMutableLiveData.postValue(
                    servicioSolicitadoRemoteRepository.agregarServicioSolicitado(servicioSolicitado)
            );

        });
    }

    public MutableLiveData<Boolean> getBooleanMutableLiveData() {
        return booleanMutableLiveData;
    }

    // TODO: Implement the ViewModel
}