package com.pe.valquiriaapp.ui.reserva_fecha;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.pe.valquiriaapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ReservaFechaFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //DECLARACIONES
        View view = inflater.inflate(R.layout.fragment_reserva_fecha, container, false);
        ImageView imageViewHotelGif = view.findViewById(R.id.reserva_fecha_img_gif);
        Button buttonRangeFecha = view.findViewById(R.id.reserva_fecha_button);
        MaterialDatePicker<Pair<Long, Long>> picker =configurarCalender();

        //CONFIGURACION
        Glide.with(this).load(R.drawable.reserva_hotel).into(imageViewHotelGif);
        buttonRangeFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                picker.show(getParentFragmentManager(),picker.toString());

            }
        });
        return view;
    }

    private MaterialDatePicker<Pair<Long, Long>> configurarCalender() {
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.now());
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Ingrese le rango de fechas");
        builder.setCalendarConstraints(constraintsBuilder.build());
        MaterialDatePicker<Pair<Long, Long>> picker = builder.build();
        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                // Obtenemos la fecha seleccionada como inicio
                Long fechaInicio = selection.first;
                Long fechaFin = selection.second;

                // Obtenemos la fecha de hoy al inicio del día (00:00:00)
                Calendar calendarHoy = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendarHoy.set(Calendar.HOUR_OF_DAY, 0);
                calendarHoy.set(Calendar.MINUTE, 0);
                calendarHoy.set(Calendar.SECOND, 0);
                calendarHoy.set(Calendar.MILLISECOND, 0);

                // Creamos un calendario para la fecha de inicio seleccionada
                Calendar calendarInicio = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendarInicio.setTimeInMillis(fechaInicio);
                calendarInicio.set(Calendar.HOUR_OF_DAY, 0);
                calendarInicio.set(Calendar.MINUTE, 0);
                calendarInicio.set(Calendar.SECOND, 0);
                calendarInicio.set(Calendar.MILLISECOND, 0);

                // Verificamos si la fecha de inicio es hoy
                if (calendarInicio.getTimeInMillis() == calendarHoy.getTimeInMillis()) {
                    // Si la fecha de inicio es hoy, mostramos un diálogo de alerta
                    mostrarAlertaFechaInvalida();
                } else {
                    // Si la fecha de inicio no es hoy, procedemos con la navegación
                    NavDirections navDirections = ReservaFechaFragmentDirections
                            .actionNavigationDashboardToNavigationReservaHabitacion(fechaInicio, fechaFin);
                    Navigation.findNavController(requireView()).navigate(navDirections);
                }
            }
        });

        return picker;
    }

    /**
     * Muestra un diálogo de alerta informando que no se puede seleccionar un rango
     * de fechas que comience hoy.
     */
    private void mostrarAlertaFechaInvalida() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Fecha no válida")
                .setMessage("No puede seleccionar un rango de fechas que comience hoy. Por favor, seleccione una fecha de inicio a partir de mañana.")
                .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}