package com.pe.valquiriaapp.ui.servicio_details;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.adapters.ImagenesCarouselAdapter;

import java.util.Arrays;

public class ServicioDetailsFragment extends Fragment {

    Float precioUnitario;
    String tipoServicio;
    private ServicioDetailsViewModel mViewModel;

    public static ServicioDetailsFragment newInstance() {
        return new ServicioDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servicio_details, container, false);
        mViewModel = new ViewModelProvider(this).get(ServicioDetailsViewModel.class);
        NestedScrollView constraintLayout = view.findViewById(R.id.servicio_details_contrint);
        RecyclerView recyclerView = view.findViewById(R.id.servicio_details_img);
        TextView textViewDescription = view.findViewById(R.id.servicio_details_description);
        TextView textViewPrecioUni = view.findViewById(R.id.servicio_details_price);
        MaterialButton materialButtonPlus = view.findViewById(R.id.servicio_details_button_plus);
        MaterialButton materialButtonLess = view.findViewById(R.id.servicio_details_button_less);
        EditText editText = view.findViewById(R.id.servicio_details_cantidad);
        TextView textViewPrecioTotal = view.findViewById(R.id.servicio_details_precio_total);
        Button buttonConfirmar = view.findViewById(R.id.servicio_details_confirmar);
        CircularProgressIndicator circularProgressIndicator = view.findViewById(R.id.servicio_details_loading);

        int ID ;

        constraintLayout.setVisibility(View.GONE);
        circularProgressIndicator.setVisibility(View.VISIBLE);
        ID = ServicioDetailsFragmentArgs.fromBundle(getArguments()).getIdServicio();
        mViewModel.getDatos(ID);

        materialButtonPlus.setOnClickListener(view1 -> {
            editText.setText(String.valueOf(Integer.parseInt(editText.getText().toString()) + 1 ));
            recalcularPrecioTotal(textViewPrecioUni,editText,textViewPrecioTotal);
        });

        materialButtonLess.setOnClickListener(view1 -> {
            if (Integer.parseInt(editText.getText().toString())>1){
                editText.setText(String.valueOf(Integer.parseInt(editText.getText().toString()) - 1 ));
                recalcularPrecioTotal(textViewPrecioUni,editText,textViewPrecioTotal);
            }
        });

        buttonConfirmar.setOnClickListener(view1 -> {
            mViewModel.pasarServicioSolicitado(ID,Integer.parseInt(editText.getText().toString()));
            buttonConfirmar.setEnabled(false);
        });
        /*
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String nuevoTexto = editable.toString();
                String Total =String.valueOf(Float.parseFloat( textViewPrecioUni.getText().toString())
                        * Float.parseFloat(nuevoTexto)) ;
                textViewPrecioTotal.setText(Total);
            }
        });
        */

        mViewModel.getBooleanMutableLiveData().observe(getViewLifecycleOwner(),aBoolean -> {
            if (aBoolean){
                // Navegar a billing en lugar de directamente a servicios
                float totalAmount = Float.parseFloat(textViewPrecioTotal.getText().toString());
                NavDirections navDirections = ServicioDetailsFragmentDirections
                        .actionServicioDetailsFragmentToBillingFragment("servicio", totalAmount);
                Navigation.findNavController(view).navigate(navDirections);
            }else{
                Toast.makeText(view.getContext(), "ERROR, COMUNIQUESE CON NOSOTROS SI EL ERROR PERSISTE", Toast.LENGTH_SHORT).show();
                NavDirections navDirections = ServicioDetailsFragmentDirections.actionServicioDetailsFragmentToServicioTiposFragment2();
                Navigation.findNavController(view).navigate(navDirections);
            }
        });
        mViewModel.getServicioMutableLiveData().observe(getViewLifecycleOwner(),servicio -> {
            ImagenesCarouselAdapter imagenesCarouselAdapter = new ImagenesCarouselAdapter(Arrays.asList(servicio.getImagen()));
            recyclerView.setAdapter(imagenesCarouselAdapter);
            textViewDescription.setText(servicio.getDescripcion());
            textViewPrecioTotal.setText(String.valueOf(
                    servicio.getPrecio()
            ));
            textViewPrecioUni.setText(String.valueOf(
                    servicio.getPrecio()
            ));
            tipoServicio = servicio.getTipoServicio().getTipo();
            precioUnitario = servicio.getPrecio();
            constraintLayout.setVisibility(View.VISIBLE);
            circularProgressIndicator.setVisibility(View.GONE);
        });

        return view;
    }

    private void recalcularPrecioTotal(TextView precio,EditText cantidad, TextView total){
        String Total =String.valueOf(Float.parseFloat( precio.getText().toString())
                * Float.parseFloat(cantidad.getText().toString())) ;
        total.setText(Total);
    }


}