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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.model.Habitacion;

public class ReservaDetailsFragment extends Fragment {

    private ReservaDetailsViewModel mViewModel;
    private ImageView reserva_details_img;
    private TextView reservaDetailsTipo;
    private TextView reservaDetailsDescription;
    private TextView reservaDetailsPiso;
    private TextView reservaDetailsCodigo;
    private TextView reservaDetailsFecha1;
    private TextView reservaDetailsFecha2;
    private TextView reservaDetailsDias;
    private TextView reservaDetailsPrecioDia;
    private EditText reservaDetailsComentario;
    private ProgressBar reservaDetailsLoading;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private LinearLayout reservaDetailsLayout;
    private CardView reservaDetailsCardView;
    private TextView reservaDetailsPrecioTotal;
    private Button reserva_details_confirmar;
    private int codHabitacion;
    private int totalDias;
    private String inicioDate;
    private String finDate;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //declaraciones
        View view = inflater.inflate(R.layout.fragment_reserva_details, container, false);
        reserva_details_img = view.findViewById(R.id.reserva_details_img);
        reservaDetailsTipo = view.findViewById(R.id.reserva_details_tipo);
        reservaDetailsDescription = view.findViewById(R.id.reserva_details_description);
        reservaDetailsPiso = view.findViewById(R.id.reserva_details_piso);
        reservaDetailsCodigo = view.findViewById(R.id.reserva_details_codigo);
        reservaDetailsFecha1 = view.findViewById(R.id.reserva_details_fecha1);
        reservaDetailsFecha2 = view.findViewById(R.id.reserva_details_fecha2);
        reservaDetailsDias = view.findViewById(R.id.reserva_details_dias);
        reservaDetailsPrecioDia = view.findViewById(R.id.reserva_details_precio_dia);
        reservaDetailsComentario = view.findViewById(R.id.reserva_details_comentario);
        reservaDetailsLoading = view.findViewById(R.id.reserva_details_loading);
        reservaDetailsLayout = view.findViewById(R.id.reserva_details_layout);
        reservaDetailsCardView = view.findViewById(R.id.reserva_details_cardView);
        reservaDetailsPrecioTotal = view.findViewById(R.id.reserva_details_precio_total);
        reserva_details_confirmar = view.findViewById(R.id.reserva_details_confirmar);
        mViewModel = new ViewModelProvider(this).get(ReservaDetailsViewModel.class);
        //cpnfiguracion
        reservaDetailsLoading.setVisibility(View.VISIBLE);
        reservaDetailsLayout.setVisibility(View.GONE);
        reservaDetailsCardView.setVisibility(View.GONE);
        reserva_details_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.pasarDatosAlojamiento(
                    codHabitacion,
                    inicioDate,
                        finDate,
                        String.valueOf(reservaDetailsComentario.getText())

                );
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
        mViewModel.getBooleanMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    NavDirections navDirections = ReservaDetailsFragmentDirections.actionNavigationReservaDetailsToHabitacionFragment();
                    Navigation.findNavController(view).navigate(navDirections);
                }else{
                    Toast.makeText(getContext(), "RESERVA FALLIFA, POR FAVOR INTENTELO DENUEVO", Toast.LENGTH_LONG).show();
                    NavDirections navDirections = ReservaDetailsFragmentDirections.actionNavigationReservaDetailsToNavigationReservaFecha();
                    Navigation.findNavController(view).navigate(navDirections);
                }
            }
        });
        mViewModel.getHabitacionMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Habitacion>() {
            @Override
            public void onChanged(Habitacion habitacion) {
                Glide.with(view)
                        .load(habitacion.getImagenes()[0])
                        .placeholder(R.drawable.loading_gift)
                        .error(R.drawable.item_habitacion_noload)
                        .into(reserva_details_img);
                reservaDetailsTipo.setText(habitacion.getTipo());
                reservaDetailsDescription.setText(habitacion.getDescripcion());
                reservaDetailsPiso.setText(String.valueOf(habitacion.getPiso()));
                reservaDetailsCodigo.setText(String.valueOf(habitacion.getCodigoHabitacion()));
                reservaDetailsFecha1.setText(inicioDate);
                reservaDetailsFecha2.setText(finDate);
                reservaDetailsDias.setText(String.valueOf(totalDias));
                reservaDetailsPrecioDia.setText(Float.toString(habitacion.getPrecioDia()));
                reservaDetailsPrecioTotal.setText(Float.toString(habitacion.getPrecioDia()*totalDias));
                reservaDetailsLoading.setVisibility(View.GONE);
                reservaDetailsLayout.setVisibility(View.VISIBLE);
                reservaDetailsCardView.setVisibility(View.VISIBLE);

            }
        });

        return view;
    }


}