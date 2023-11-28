package com.pe.valquiriaapp.ui.registro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pe.valquiriaapp.model.Cliente;
import com.pe.valquiriaapp.repository.remote.ClienteRemoteRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RegistroViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private Cliente cliente;
    private MutableLiveData<Boolean> mutableLiveDataConfirmar = new MutableLiveData<>();

    public void pasarUsuario(int dni, String nombre, String apellido, String correo, String contrasena){
        cliente = new Cliente(dni,nombre,apellido,correo,contrasena);
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ClienteRemoteRepository clienteRemoteRepository = new ClienteRemoteRepository();
                mutableLiveDataConfirmar.postValue(clienteRemoteRepository.insertarCliente(cliente));
            }
        });
    }
    public MutableLiveData<Boolean> getMutableLiveDataConfirmar() {
        return mutableLiveDataConfirmar;
    }
}