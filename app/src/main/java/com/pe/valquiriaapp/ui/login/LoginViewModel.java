package com.pe.valquiriaapp.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.pe.valquiriaapp.model.Cliente;
import com.pe.valquiriaapp.repository.local.ClienteLocalRepository;
import com.pe.valquiriaapp.repository.local.ClienteLocalRepositoryTest;
import com.pe.valquiriaapp.repository.remote.ClienteRemoteRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginViewModel extends AndroidViewModel {

    //data mutable
    private MutableLiveData<Boolean> confirmacion = new MutableLiveData<>();

    ClienteLocalRepository clienteLocalRepository ;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        clienteLocalRepository = new ClienteLocalRepository(application);
    }

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
                    if (aBoolean){
                        Cliente clienteData =
                                clienteRemoteRepository.clientePorCorreo(cliente.getCorreo());
                        clienteLocalRepository.borrarDatosCliente();
                        clienteLocalRepository.guardarDatosCliente(clienteData);
                        System.out.println(clienteLocalRepository.obtenerDatosCliente().getDni());
                    }
                    confirmacion.postValue(aBoolean);
                }catch (Exception e) {
                    System.out.println("Error inesperado: " + e.getMessage());
                    e.printStackTrace();
                }

            }
        });
    }
}