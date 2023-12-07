package com.pe.valquiriaapp.repository.local;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.pe.valquiriaapp.model.Cliente;

public class ClienteLocalRepository {
    private static final String PREFS_NAME = "ClientPrefs";
    private static final String CLIENT_KEY = "clientData";

    private final SharedPreferences preferences;

    public ClienteLocalRepository(Context context) {
        this.preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }


    public void guardarDatosCliente(Cliente cliente) {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String clienteJson = gson.toJson(cliente);
        editor.putString(CLIENT_KEY, clienteJson);
        editor.apply();
    }

    public Cliente obtenerDatosCliente() {
        String clienteJson = preferences.getString(CLIENT_KEY, null);
        if (clienteJson != null) {
            Gson gson = new Gson();
            return gson.fromJson(clienteJson, Cliente.class);
        }
        return null;
    }

    public void borrarDatosCliente() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(CLIENT_KEY);
        editor.apply();
    }

    public boolean existeInformacionCliente() {
        return preferences.contains(CLIENT_KEY);
    }
}
