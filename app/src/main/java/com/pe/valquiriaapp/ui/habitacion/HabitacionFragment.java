package com.pe.valquiriaapp.ui.habitacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.pe.valquiriaapp.R;

public class HabitacionFragment extends Fragment {

    private HabitacionFragment binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_habitacion,container,false);
       HabitacionViewModel habitacionViewModel = new ViewModelProvider(this).get(HabitacionViewModel.class);
       TextView textViewCodHabitacion = view.findViewById(R.id.habitacion_codigo);
       textViewCodHabitacion.setText(
               String.valueOf(habitacionViewModel.getCodigoHabitacion())
       );
        Button buttonToServicios = view.findViewById(R.id.habitacion_to_servicios);
        buttonToServicios.setOnClickListener(view1 -> {
            buttonToServicios.setEnabled(false);
            NavDirections navDirections = HabitacionFragmentDirections.actionHabitacionFragmentToNavigationServicioValidator();
            Navigation.findNavController(getView()).navigate(navDirections);
        });
       return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}