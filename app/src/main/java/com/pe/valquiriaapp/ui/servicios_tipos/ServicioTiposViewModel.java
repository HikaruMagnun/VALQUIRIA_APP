package com.pe.valquiriaapp.ui.servicios_tipos;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pe.valquiriaapp.model.ServicioTipo;
import com.pe.valquiriaapp.repository.remote.ServicioTipoRemoteRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServicioTiposViewModel extends ViewModel {
    MutableLiveData<List<ServicioTipo>> listServicioTipoMutableLiveData =  new MutableLiveData<>();
    ServicioTipoRemoteRepository servicioTipoRemoteRepository;
    public void consultarTipos() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            servicioTipoRemoteRepository = new ServicioTipoRemoteRepository();
            listServicioTipoMutableLiveData.postValue(
                    servicioTipoRemoteRepository.listarMueblesTipo()
            );
        });
    }

    public MutableLiveData<List<ServicioTipo>> getListServicioTipoMutableLiveData() {
        return listServicioTipoMutableLiveData;
    }
    // TODO: Implement the ViewModel
}