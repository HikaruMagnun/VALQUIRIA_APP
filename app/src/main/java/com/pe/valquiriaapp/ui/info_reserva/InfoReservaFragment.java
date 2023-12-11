package com.pe.valquiriaapp.ui.info_reserva;

import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.model.Alojamiento;
import com.pe.valquiriaapp.model.ServicioSolicitado;

import java.util.Arrays;
import java.util.List;

public class InfoReservaFragment extends Fragment {

    private InfoReservaViewModel mViewModel;

    public static InfoReservaFragment newInstance() {
        return new InfoReservaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_reserva, container, false);
        NestedScrollView scrollView = view.findViewById(R.id.info_reserva_scroll);
        TextView textViewCodHab = view.findViewById(R.id.info_reserva_cod_hab);
        TextView textViewFechaAloj = view.findViewById(R.id.info_reserva_fecha);
        TextView textViewFechaVen = view.findViewById(R.id.info_reserva_vencimiento);
        TextView textViewPerso = view.findViewById(R.id.info_reserva_perso);
        TextView textViewServicios= view.findViewById(R.id.info_reserva_servicios);
        ProgressBar progressBar = view.findViewById(R.id.info_reserva_progressBar);
        mViewModel = new ViewModelProvider(this).get(InfoReservaViewModel.class);
        mViewModel.cargarDatos();

        mViewModel.getBooleanMutableLiveData().observe(getViewLifecycleOwner(), aboolean -> {
            if (aboolean){
                Alojamiento alojamiento = mViewModel.getAlojamiento();
                textViewCodHab.setText(String.valueOf(alojamiento.getHabitacion().getCodigoHabitacion()));
                textViewFechaAloj.setText(alojamiento.getFechaAlojamiento().toString());
                textViewFechaVen.setText(alojamiento.getFechaAlojamientoVencimiento().toString());

                List<String> lista = convertirCadenaALista(alojamiento.getComentario());
                StringBuilder resultado = new StringBuilder();
                for (String elemento : lista) {
                    resultado.append("⁍ ").append(elemento).append("\n");
                }
                textViewPerso.setText(resultado.toString());
                List<ServicioSolicitado> servicioSolicitados = mViewModel.getServicioSolicitadoList();
                if (servicioSolicitados.size() == 0) {
                    textViewServicios.setText("NO SE ENCONTRO SERVICIOS SOLICITADOS EN SU RESERVA");
                } else {
                    StringBuilder builder = new StringBuilder();
                    for (ServicioSolicitado servicioSolicitado :
                            servicioSolicitados) {
                        builder.append("⁍ ").append(servicioSolicitado.getServicio().getDescripcion()).append("\t\t")
                                .append(servicioSolicitado.getCantidad()).append("\t\t")
                                .append(servicioSolicitado.getEstado() ? "ENTREGADO" : "PENDIENTE")
                                .append("\n");
                    }
                    textViewServicios.setText(builder.toString());
                }

                progressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    private List<String> convertirCadenaALista(String cadena) {
        String[] elementos = cadena.replaceAll("\\[|\\]", "").split(",\\s*");
        List<String> lista = Arrays.asList(elementos);
        return lista;
    }

}