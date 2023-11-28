package com.pe.valquiriaapp.ui.reserva_habitacion;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pe.valquiriaapp.model.Habitacion;
import com.pe.valquiriaapp.repository.remote.HabitacionRemoteRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReservaHabitacionViewModel extends ViewModel {

    private List<Habitacion> habitacionList ;
    private MutableLiveData<List<Habitacion>> listHabitacionMutableLiveData = new MutableLiveData<>();

    private HabitacionRemoteRepository habitacionRemoteRepository;

    public  ReservaHabitacionViewModel(){
        habitacionList = new ArrayList<>();

    }

    public void pasarDatos(LocalDate firstDate, LocalDate finDate){
        cargarHabitaciones();
    }
    private void cargarHabitaciones() {
        Executor executor =Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    habitacionRemoteRepository = new HabitacionRemoteRepository();
                    habitacionList =    habitacionRemoteRepository.ListarHabitaciones();
                    listHabitacionMutableLiveData.postValue(habitacionList);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }

        });
    }

    public MutableLiveData<List<Habitacion>> getListHabitacionMutableLiveData() {
        return listHabitacionMutableLiveData;
    }
}