package com.pe.valquiriaapp.ui.reserva_fecha;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReservaFechaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ReservaFechaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}