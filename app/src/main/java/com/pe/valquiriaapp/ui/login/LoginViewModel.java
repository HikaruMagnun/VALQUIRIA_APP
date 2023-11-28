package com.pe.valquiriaapp.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pe.valquiriaapp.model.Cliente;
import com.pe.valquiriaapp.repository.remote.ClienteRemoteRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginViewModel extends ViewModel {

    //data mutable
    private MutableLiveData<Boolean> confirmacion = new MutableLiveData<>();
    public MutableLiveData<Boolean> getConfirmacion() {
        return confirmacion;
    }

    //funciones
    public void pasarCredenciales(Cliente cliente){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ClienteRemoteRepository clienteRemoteRepository = new ClienteRemoteRepository();
                    Boolean aBoolean = clienteRemoteRepository.autenticarCliente(cliente);
                    confirmacion.postValue(aBoolean);
                }catch (Exception e) {
                    System.out.println("Error inesperado: " + e.getMessage());
                    e.printStackTrace();
                }

            }
        });
    }
}