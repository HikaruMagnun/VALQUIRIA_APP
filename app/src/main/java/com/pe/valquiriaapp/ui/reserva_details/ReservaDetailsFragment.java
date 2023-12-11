package com.pe.valquiriaapp.ui.reserva_details;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.adapters.ImagenesCarouselAdapter;
import com.pe.valquiriaapp.adapters.TipoMuebleAdapter;
import com.pe.valquiriaapp.model.Habitacion;
import com.pe.valquiriaapp.model.MueblesTipo;

import java.util.Arrays;
import java.util.List;

public class ReservaDetailsFragment extends Fragment {

    private ReservaDetailsViewModel mViewModel;
    private RecyclerView reservaDetailsRecyclerView;
    private RecyclerView reservaDetailsTipoMuebles;
    private TextView reservaDetailsTipo;
    private TextView reservaDetailsDescription;
    private TextView reservaDetailsPiso;
    private TextView reservaDetailsCodigo;
    private TextView reservaDetailsFecha1;
    private TextView reservaDetailsFecha2;
    private TextView reservaDetailsDias;
    private TextView reservaDetailsPrecioDia;
    private ProgressBar reservaDetailsLoading;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private LinearLayout reservaDetailsLayout;
    private CardView reservaDetailsCardView;
    private TextView reservaDetailsPrecioTotal;
    private MaterialSwitch reservaDetailsMaterialSwitchPersonalizacionConfirm;
    private Button reservaDetailsConfirmar;

    private CircularProgressIndicator reservaDetailsTipoMueblesLoading;

    private int codHabitacion;
    private int totalDias;
    private String inicioDate;
    private String finDate;

    private Float precioTotalNoCustom;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //declaraciones
        View view = inflater.inflate(R.layout.fragment_reserva_details, container, false);
        reservaDetailsRecyclerView = view.findViewById(R.id.reserva_details_recycler_img);
        reservaDetailsTipoMuebles = view.findViewById(R.id.reserva_details_tipo_muebles);
        reservaDetailsTipo = view.findViewById(R.id.reserva_details_tipo);
        reservaDetailsDescription = view.findViewById(R.id.reserva_details_description);
        reservaDetailsPiso = view.findViewById(R.id.reserva_details_piso);
        reservaDetailsCodigo = view.findViewById(R.id.reserva_details_codigo);
        reservaDetailsFecha1 = view.findViewById(R.id.reserva_details_fecha1);
        reservaDetailsFecha2 = view.findViewById(R.id.reserva_details_fecha2);
        reservaDetailsDias = view.findViewById(R.id.reserva_details_dias);
        reservaDetailsPrecioDia = view.findViewById(R.id.reserva_details_precio_dia);
        reservaDetailsLoading = view.findViewById(R.id.reserva_details_loading);
        reservaDetailsLayout = view.findViewById(R.id.reserva_details_layout);
        reservaDetailsCardView = view.findViewById(R.id.reserva_details_cardView);
        reservaDetailsPrecioTotal = view.findViewById(R.id.reserva_details_precio_total);
        reservaDetailsConfirmar = view.findViewById(R.id.reserva_details_confirmar);
        reservaDetailsTipoMueblesLoading = view.findViewById(R.id.reserva_details_tipo_muebles_loading);
        reservaDetailsMaterialSwitchPersonalizacionConfirm =
                view.findViewById(R.id.reserva_details_personalizacion_confirmacion);
        mViewModel = new ViewModelProvider(this).get(ReservaDetailsViewModel.class);
        //cpnfiguracion
        reservaDetailsLoading.setVisibility(View.VISIBLE);
        reservaDetailsLayout.setVisibility(View.GONE);
        reservaDetailsCardView.setVisibility(View.GONE);
        reservaDetailsTipoMueblesLoading.setVisibility(View.GONE);
        reservaDetailsTipoMuebles.setVisibility(View.GONE);
        reservaDetailsConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservaDetailsConfirmar.setEnabled(false);
                String comentario;
                if(reservaDetailsMaterialSwitchPersonalizacionConfirm.isChecked()){
                    comentario = mViewModel.getComentarioAlojamiento().toString();
                }else {
                    comentario = "SIN NADA AÑADIDO";
                }
                mViewModel.pasarDatosAlojamiento(
                    codHabitacion,
                    inicioDate,
                        finDate,
                      comentario

                );

            }
        });

        reservaDetailsMaterialSwitchPersonalizacionConfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if(! mViewModel.getConfirmaCargaTipoMuebleCarga()){
                        mViewModel.cargarTipoMubles();
                        reservaDetailsMaterialSwitchPersonalizacionConfirm.setEnabled(false);
                    }else {
                        reservaDetailsPrecioTotal.setText(
                                Float.toString(
                                        mViewModel.getFloatPrecioTotalMutableLiveData().getValue()
                                )
                        );
                        reservaDetailsTipoMueblesLoading.setVisibility(View.GONE);
                        reservaDetailsTipoMuebles.setVisibility(View.VISIBLE);

                    }

                }else {
                    reservaDetailsTipoMueblesLoading.setVisibility(View.GONE);
                    reservaDetailsTipoMuebles.setVisibility(View.GONE);
                    reservaDetailsPrecioTotal.setText(Float.toString(precioTotalNoCustom));
                }
            }
        });
        //recogo de valores
        codHabitacion = ReservaDetailsFragmentArgs.fromBundle(getArguments()).getCodHabitacion();
        totalDias = ReservaDetailsFragmentArgs.fromBundle(getArguments()).getTotalDias();
        inicioDate = ReservaDetailsFragmentArgs.fromBundle(getArguments()).getInicioDate();
        finDate = ReservaDetailsFragmentArgs.fromBundle(getArguments()).getFinDate();
        //paso de datos al view model
        mViewModel.pasarDatos(codHabitacion);
        //recogo de datos obsercer

        mViewModel.getListMueblesTipoMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<MueblesTipo>>() {
            @Override
            public void onChanged(List<MueblesTipo> mueblesTipos) {
                TipoMuebleAdapter tipoMuebleAdapter = new TipoMuebleAdapter(mueblesTipos,mViewModel);
                reservaDetailsTipoMuebles.setAdapter(tipoMuebleAdapter);
                reservaDetailsTipoMueblesLoading.setVisibility(View.GONE);
                reservaDetailsTipoMuebles.setVisibility(View.VISIBLE);
                reservaDetailsMaterialSwitchPersonalizacionConfirm.setEnabled(true);
            }
        });
        mViewModel.getBooleanMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    NavDirections navDirections = ReservaDetailsFragmentDirections.actionReservaDetailsToNavigationAlojamientoValidator();
                    Navigation.findNavController(view).navigate(navDirections);
                }else{
                    Toast.makeText(getContext(), "RESERVA FALLIFA, POR FAVOR INTENTELO DENUEVO", Toast.LENGTH_LONG).show();
                    NavDirections navDirections = ReservaDetailsFragmentDirections.actionReservaDetailsToNavigationAlojamientoValidator();
                    Navigation.findNavController(view).navigate(navDirections);
                }
            }
        });
        mViewModel.getHabitacionMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Habitacion>() {
            @Override
            public void onChanged(Habitacion habitacion) {
                ImagenesCarouselAdapter imagenesCarouselAdapter = new ImagenesCarouselAdapter(Arrays.asList(habitacion.getImagenes()));
                reservaDetailsRecyclerView.setAdapter(imagenesCarouselAdapter);
                reservaDetailsTipo.setText(habitacion.getTipo());
                reservaDetailsDescription.setText(habitacion.getDescripcion());
                reservaDetailsPiso.setText(String.valueOf(habitacion.getPiso()));
                reservaDetailsCodigo.setText(String.valueOf(habitacion.getCodigoHabitacion()));
                reservaDetailsFecha1.setText(inicioDate);
                reservaDetailsFecha2.setText(finDate);
                reservaDetailsDias.setText(String.valueOf(totalDias));
                reservaDetailsPrecioDia.setText(Float.toString(habitacion.getPrecioDia()));
                mViewModel.getFloatPrecioTotalMutableLiveData().
                        postValue(habitacion.getPrecioDia()*totalDias);
                precioTotalNoCustom = habitacion.getPrecioDia()*totalDias;
                reservaDetailsLoading.setVisibility(View.GONE);
                reservaDetailsLayout.setVisibility(View.VISIBLE);
                reservaDetailsCardView.setVisibility(View.VISIBLE);

            }
        });

        mViewModel.getFloatPrecioTotalMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                reservaDetailsPrecioTotal.setText(Float.toString(
                        aFloat));
            }
        });

        return view;
    }


}