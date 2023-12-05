package com.pe.valquiriaapp.ui.reserva_details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pe.valquiriaapp.model.Alojamiento;
import com.pe.valquiriaapp.model.Habitacion;
import com.pe.valquiriaapp.model.MueblesTipo;
import com.pe.valquiriaapp.repository.remote.AlojamientoRemoteRepository;
import com.pe.valquiriaapp.repository.remote.HabitacionRemoteRepository;
import com.pe.valquiriaapp.repository.remote.MuebleRemoteRepository;
import com.pe.valquiriaapp.repository.remote.MuebleTipoRemoteRepository;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReservaDetailsViewModel extends ViewModel {
    private MutableLiveData<Habitacion> habitacionMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<MueblesTipo>> listMueblesTipoMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();

    //private MutableLiveData<List<Boolean>> mueblesCarga = new MutableLiveData<>();
    private Boolean confirmaCargaTipoMuebleCarga = false;
    private Habitacion habitacion;
    private int cod;
    private MutableLiveData<Float> integerPrecioTotalMutableLiveData = new MutableLiveData<>();

    private StringBuilder ComentarioAlojamiento = new StringBuilder();
    private HabitacionRemoteRepository habitacionRemoteRepository;
    private AlojamientoRemoteRepository alojamientoRemoteRepository;
    private MuebleTipoRemoteRepository muebleTipoRemoteRepository;
    private MuebleRemoteRepository muebleRemoteRepository;

    public void pasarDatos(int cod){
        this.cod=cod;
        cargarDatos();
    }
    public void pasarDatosAlojamiento(int codHabitacion, String inicioDate, String finDate, String comentario){
        Alojamiento alojamiento = new Alojamiento();
        Habitacion habitacion1 = new Habitacion();
        habitacion1.setCodigoHabitacion(codHabitacion);
        alojamiento.setHabitacion(habitacion1);
        alojamiento.setFechaAlojamiento(Date.valueOf(inicioDate));
        alojamiento.setFechaAlojamientoVencimiento(Date.valueOf(finDate));
        alojamiento.setComentario(comentario);
        alojamiento.setEstadoReserva("pendiente");
        cargarInsercion(alojamiento);
    }

    private void cargarInsercion(Alojamiento alojamiento){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    alojamientoRemoteRepository = new AlojamientoRemoteRepository();
                    Boolean confirmar =    alojamientoRemoteRepository.agregarAlojamiento(alojamiento);
                    booleanMutableLiveData.postValue(confirmar);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }

        });
    }

    public void cargarTipoMubles(){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    muebleTipoRemoteRepository = new MuebleTipoRemoteRepository();
                    muebleRemoteRepository = new MuebleRemoteRepository();
                    List<MueblesTipo> mueblesTipoList = muebleTipoRemoteRepository.listarMueblesTipo();

                    for (MueblesTipo mueblesTipo:
                         mueblesTipoList) {
                        mueblesTipo.setMuebleList(
                                muebleRemoteRepository.listMueblesPorTipo(
                                        mueblesTipo.getTipo()
                                )
                        );
                    }
                    listMueblesTipoMutableLiveData.postValue(mueblesTipoList
                    );
                    confirmaCargaTipoMuebleCarga = true;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }

        });
    }
    private void cargarDatos(){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    habitacionRemoteRepository = new HabitacionRemoteRepository();
                    habitacion =    habitacionRemoteRepository.habitacionPorCodigo(cod);
                    habitacionMutableLiveData.postValue(habitacion);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }

        });
    }

    public void cargarMueblesPorTipo(String tipo, int position) {

    }

    public StringBuilder getComentarioAlojamiento() {
        return ComentarioAlojamiento;
    }

    public void setComentarioAlojamiento(StringBuilder comentarioAlojamiento) {
        ComentarioAlojamiento = comentarioAlojamiento;
    }



    public MutableLiveData<Habitacion> getHabitacionMutableLiveData() {
        return habitacionMutableLiveData;
    }

    public MutableLiveData<List<MueblesTipo>> getListMueblesTipoMutableLiveData() {
        return listMueblesTipoMutableLiveData;
    }

    public Boolean getConfirmaCargaTipoMuebleCarga() {
        return confirmaCargaTipoMuebleCarga;
    }

    public MutableLiveData<Boolean> getBooleanMutableLiveData() {
        return booleanMutableLiveData;
    }

    /*public MutableLiveData<List<Boolean>> getMueblesCarga() {
        return mueblesCarga;
    }*/

    public MutableLiveData<Float> getFloatPrecioTotalMutableLiveData() {
        return integerPrecioTotalMutableLiveData;
    }


}