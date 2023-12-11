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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.pe.valquiriaapp.R;

public  class InfoValidatorFragment extends Fragment {

    private ValidatorAlojamientoInfoServicioViewModel mViewModel;

    public static InfoValidatorFragment newInstance() {
        return new InfoValidatorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_validator, container, false);
        ImageView imageViewImg = view.findViewById(R.id.info_validator_img);
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
                    NavDirections navDirections = InfoValidatorFragmentDirections.actionInfoValidatorFragmentToInfoNoReserva();
                    Navigation.findNavController(view).navigate(navDirections);
                    break;
                } case 2:
                    case 3:
                {
                    NavDirections navDirections = InfoValidatorFragmentDirections.actionInfoValidatorFragmentToInfoReservaFragment();
                    Navigation.findNavController(view).navigate(navDirections);
                    break;
                }
            }
        });

        return view;
    }



}
