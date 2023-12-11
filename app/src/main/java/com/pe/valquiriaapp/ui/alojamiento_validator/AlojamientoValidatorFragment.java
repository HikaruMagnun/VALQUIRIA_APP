package com.pe.valquiriaapp.ui.alojamiento_validator;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pe.valquiriaapp.R;

public class AlojamientoValidatorFragment extends Fragment {

    private ValidatorAlojamientoInfoServicioViewModel mViewModel;

    public static AlojamientoValidatorFragment newInstance() {
        return new AlojamientoValidatorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alojamiento_validator, container, false);
        ImageView imageViewImg = view.findViewById(R.id.alojamiento_validator_img);
        ValidatorAlojamientoInfoServicioViewModel validatorAlojamientoServicioViewModel = new ViewModelProvider(this).get(ValidatorAlojamientoInfoServicioViewModel.class);

        Glide.with(view.getContext()).load(R.drawable.hotel_standby)
                .into(imageViewImg);
        validatorAlojamientoServicioViewModel.validarAlojamiento();

        validatorAlojamientoServicioViewModel.integerMutableLiveData.observe(getViewLifecycleOwner(), integer -> {
            System.out.println("CODIGO 00" + integer.toString() );
            switch (integer){
                //1=sin alojamientp
                //2=alojamiento pendiente  PENDIENTE
                //3= actualmente alojado   ALOJADO
                case 1 :{
                    NavDirections navDirections = AlojamientoValidatorFragmentDirections.actionAlojamientoValidatorToNavigationReservaFecha();
                    Navigation.findNavController(view).navigate(navDirections);
                    break;
                } case 2:{
                    NavDirections navDirections = AlojamientoValidatorFragmentDirections.actionNavigationAlojamientoValidatorToHabitacionEsperaFragment();
                    Navigation.findNavController(view).navigate(navDirections);
                    break;
                }case 3:{
                    NavDirections navDirections = AlojamientoValidatorFragmentDirections.actionAlojamientoValidatorToHabitacionFragment();
                    Navigation.findNavController(view).navigate(navDirections);
                    break;
                }
            }
        });

        return view;
    }



}