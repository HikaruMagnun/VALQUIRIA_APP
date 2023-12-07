package com.pe.valquiriaapp.ui.registro;

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
import android.widget.TextView;
import android.widget.Toast;

import com.pe.valquiriaapp.R;
import com.pe.valquiriaapp.model.Cliente;

public class RegistroFragment extends Fragment {

    private RegistroViewModel registroViewModel;

    public static RegistroFragment newInstance() {
        return new RegistroFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        // declaracion
        EditText editTextNombre = view.findViewById(R.id.registro_nombre);
        EditText editTextApellido = view.findViewById(R.id.registro_apellido);
        EditText editTextCorreo = view.findViewById(R.id.registro_correo);
        EditText editTextDni = view.findViewById(R.id.registro_dni);
        EditText editTextContrasena = view.findViewById(R.id.registro_contrasena);
        EditText editTextTelefono = view.findViewById(R.id.registro_telefono);
        EditText editTextDireccion = view.findViewById(R.id.registro_direccion);
        Button buttonRegistrar = view.findViewById(R.id.registro_button);
        TextView textViewLogin = view.findViewById(R.id.registro_login);
        registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);
        //presionable
        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextNombre.getText().toString();
                String apellido = editTextApellido.getText().toString();
                String correo = editTextCorreo.getText().toString();
                String dni = editTextDni.getText().toString();
                String contrasena = editTextContrasena.getText().toString();
                String telefono = editTextTelefono.getText().toString();
                String dirreccion = editTextDireccion.getText().toString();
                if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || dni.isEmpty() || contrasena.isEmpty()
                || telefono.isEmpty() || dirreccion.isEmpty()) {
                    Toast.makeText(view.getContext(), "Rellene los formularios", Toast.LENGTH_SHORT).show();
                    return;
                }else if (dni.length() != 8 ){
                    Toast.makeText(view.getContext(), "Introdusca un dni valido", Toast.LENGTH_SHORT).show();
                    return;
                } else if (telefono.length() != 9) {
                    Toast.makeText(getContext(), "Numero telefonico no valido", Toast.LENGTH_SHORT).show();
                }
                int dniNumber = Integer.parseInt(dni);
                registroViewModel.pasarUsuario(dniNumber,nombre,apellido,correo,contrasena,
                        Integer.parseInt(telefono),dirreccion);
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections navDirections = RegistroFragmentDirections.actionRegistroToLogin();
                Navigation.findNavController(getView()).navigate(navDirections);
            }
        });
        //OBSERVER
        registroViewModel.getMutableLiveDataConfirmar().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    NavDirections navDirections = RegistroFragmentDirections.actionRegistroToActivityApp();
                    Navigation.findNavController(getView()).navigate(navDirections);
                }else {
                    Toast.makeText(getContext(), "CORREO O DNI YA REGISTRADO", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);
        // TODO: Use the ViewModel
    }

}