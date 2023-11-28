package com.pe.valquiriaapp.ui.reserva_fecha;

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
        builder.setSelection(new Pair<>(MaterialDatePicker.todayInUtcMilliseconds(),null));
        MaterialDatePicker<Pair<Long, Long>> picker = builder.build();
        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                Long firsDate = selection.first;
                Long secondDate = selection.second;
                NavDirections navDirections = ReservaFechaFragmentDirections.actionNavigationDashboardToNavigationReservaHabitacion(firsDate,secondDate);
                Navigation.findNavController(requireView()).navigate(navDirections);
            }
        });

        return picker;
    }


}