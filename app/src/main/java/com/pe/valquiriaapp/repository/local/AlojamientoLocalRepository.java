package com.pe.valquiriaapp.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.pe.valquiriaapp.model.Alojamiento;

public class AlojamientoLocalRepository {

    private static final String PREFS_NAME = "AlojamientoPrefs";
    private static final String ALOJAMIENTO_KEY = "alojamientoData";

    private final SharedPreferences preferences;

    public AlojamientoLocalRepository(Context context) {
        this.preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void guardarDatosAlojamiento(Alojamiento alojamiento) {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String alojamientoJson = gson.toJson(alojamiento);
        editor.putString(ALOJAMIENTO_KEY, alojamientoJson);
        editor.apply();
    }

    public Alojamiento obtenerDatosAlojamiento() {
        String alojamientoJson = preferences.getString(ALOJAMIENTO_KEY, null);
        if (alojamientoJson != null) {
            Gson gson = new Gson();
            return gson.fromJson(alojamientoJson, Alojamiento.class);
        }
        return null;
    }

    public void borrarDatosAlojamiento() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(ALOJAMIENTO_KEY);
        editor.apply();
    }

    public boolean existeInformacionAlojamiento() {
        return preferences.contains(ALOJAMIENTO_KEY);
    }
}
