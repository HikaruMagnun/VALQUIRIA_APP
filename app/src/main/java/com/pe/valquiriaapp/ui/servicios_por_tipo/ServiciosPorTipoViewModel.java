package com.pe.valquiriaapp.ui.servicios_por_tipo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pe.valquiriaapp.model.Servicio;
import com.pe.valquiriaapp.repository.remote.ServicioRemoteRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServiciosPorTipoViewModel extends ViewModel {

    ServicioRemoteRepository servicioRemoteRepository;
    MutableLiveData<List<Servicio>> listServicioMutableLiveData = new MutableLiveData<>();
    public void cargarServicioPorTipo(String tipo) {

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            servicioRemoteRepository = new ServicioRemoteRepository();
            List<Servicio> servicioList = servicioRemoteRepository.listServicioPorTipo(tipo);
            listServicioMutableLiveData.postValue(
                    servicioList
            );
        });
    }

    public MutableLiveData<List<Servicio>> getListServicioMutableLiveData() {
        return listServicioMutableLiveData;
    }

    // TODO: Implement the ViewModel
}