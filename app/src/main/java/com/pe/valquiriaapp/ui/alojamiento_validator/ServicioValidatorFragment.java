package com.pe.valquiriaapp.ui.alojamiento_validator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.pe.valquiriaapp.R;

public class ServicioValidatorFragment extends Fragment {

    private ValidatorAlojamientoServicioViewModel mViewModel;

    public static ServicioValidatorFragment newInstance() {
        return new ServicioValidatorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servicios_validator, container, false);
        ImageView imageViewImg = view.findViewById(R.id.servicio_validator_img);
        ValidatorAlojamientoServicioViewModel validatorAlojamientoServicioViewModel = new ViewModelProvider(this).get(ValidatorAlojamientoServicioViewModel.class);

        Glide.with(view.getContext()).load("https://cdn-segurosfalabella.azureedge.net/web/gifs/images/01.gif")
               .into(imageViewImg);
        validatorAlojamientoServicioViewModel.validarAlojamiento();

        validatorAlojamientoServicioViewModel.integerMutableLiveData.observe(getViewLifecycleOwner(), integer -> {
            System.out.println("CODIGO 00" + integer.toString() );
            switch (integer){
                //1=sin alojamientp
                //2=alojamiento pendiente  PENDIENTE
                //3= actualmente alojado   ALOJADO
                case 1 :{
                    //NavDirections navDirections = AlojamientoValidatorFragmentDirections.actionAlojamientoValidatorToNavigationReservaFecha();
                    //Navigation.findNavController(view).navigate(navDirections);
                    break;
                } case 2:{
                    //NavDirections navDirections = AlojamientoValidatorFragmentDirections.actionNavigationAlojamientoValidatorToHabitacionEsperaFragment();
                    //.findNavController(view).navigate(navDirections);
                    break;
                }case 3:{
                    //NavDirections navDirections = AlojamientoValidatorFragmentDirections.actionAlojamientoValidatorToHabitacionFragment();
                    //Navigation.findNavController(view).navigate(navDirections);
                    break;
                }
            }
        });

        return view;
    }


}