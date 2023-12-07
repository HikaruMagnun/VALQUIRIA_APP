package com.pe.valquiriaapp.repository.local;

import android.content.Context;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import com.google.gson.Gson;
import com.pe.valquiriaapp.model.Cliente;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class ClienteLocalRepositoryTest {
    private static final String PREFS_NAME = "client_datos";
    private static final Preferences.Key<String> CLIENT_KEY = PreferencesKeys.stringKey("client_data_class");

    private final RxDataStore<Preferences> dataStore;

    public ClienteLocalRepositoryTest(Context context) {
        dataStore = new RxPreferenceDataStoreBuilder(context, PREFS_NAME)
                .build();
    }

    public Single<Preferences> guardarDatosCliente(Cliente cliente) {
 
         return dataStore.updateDataAsync(preferences -> {
             MutablePreferences mutablePreferences = preferences.toMutablePreferences();
            Gson gson = new Gson();
            String clienteJson = gson.toJson(cliente);
            mutablePreferences.set(CLIENT_KEY,clienteJson);
             return Single.just(mutablePreferences);
        });
    }

    public Flowable<Cliente> obtenerDatosCliente() {
        return dataStore.data().map(preferences -> {
            String clienteJson = preferences.get(CLIENT_KEY);
            if (clienteJson != null) {
                Gson gson = new Gson();
                return gson.fromJson(clienteJson, Cliente.class);
            }
            return null;
        });
    }

    public Single<Preferences>   borrarDatosCliente() {
        return dataStore.updateDataAsync(preferences -> {
            MutablePreferences mutablePreferences = preferences.toMutablePreferences();
            mutablePreferences.remove(CLIENT_KEY);
            return Single.just(mutablePreferences);

        });
    }

    public Flowable<Boolean> existeInformacionCliente() {
        return dataStore.data().map(preferences -> preferences.contains(CLIENT_KEY));

    }
}
