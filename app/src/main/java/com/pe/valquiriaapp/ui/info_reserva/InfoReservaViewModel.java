package com.pe.valquiriaapp.ui.info_reserva;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.pe.valquiriaapp.model.Alojamiento;
import com.pe.valquiriaapp.model.Servicio;
import com.pe.valquiriaapp.model.ServicioSolicitado;
import com.pe.valquiriaapp.repository.local.AlojamientoLocalRepository;
import com.pe.valquiriaapp.repository.remote.ServicioRemoteRepository;
import com.pe.valquiriaapp.repository.remote.ServicioSolicitadoRemoteRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InfoReservaViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> integerMutableLiveData = new MutableLiveData<>(false);
    private Alojamiento alojamiento;
    private List<ServicioSolicitado> servicioSolicitadoList;
    private AlojamientoLocalRepository alojamientoLocalRepository;
    private ServicioSolicitadoRemoteRepository servicioSolicitadoRemoteRepository;
    private ServicioRemoteRepository servicioRemoteRepository;
    public InfoReservaViewModel(@NonNull Application application) {
        super(application);
        alojamientoLocalRepository = new AlojamientoLocalRepository(application);
    }


    public void cargarDatos() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            alojamiento = alojamientoLocalRepository.obtenerDatosAlojamiento();
            servicioSolicitadoRemoteRepository = new ServicioSolicitadoRemoteRepository();
            servicioRemoteRepository = new ServicioRemoteRepository();
            servicioSolicitadoList = servicioSolicitadoRemoteRepository.listarServiciosSolicitadosPorIdAlojamiento(alojamiento.getId());
            for (ServicioSolicitado servicioSolicitado:
                 servicioSolicitadoList) {
                Servicio servicio = servicioSolicitado.getServicio();
                servicioSolicitado.setServicio(servicioRemoteRepository.servicioPorId(servicio.getId()));
            }
            getBooleanMutableLiveData().postValue(true);
        });


    }

    public MutableLiveData<Boolean> getBooleanMutableLiveData() {
        return integerMutableLiveData;
    }

    public List<ServicioSolicitado> getServicioSolicitadoList() {
        return servicioSolicitadoList;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }
}