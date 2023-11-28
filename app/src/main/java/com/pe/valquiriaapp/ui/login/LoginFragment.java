package com.pe.valquiriaapp.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

import java.util.Objects;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        //Declaracines
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        EditText editTextCorreo = view.findViewById(R.id.login_email);
        EditText editTextContrasena = view.findViewById(R.id.login_contrasena);
        Button buttonlogin = view.findViewById(R.id.login_button);
        TextView textViewRegistrar = view.findViewById(R.id.login_registrar);

        //presionable
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = editTextCorreo.getText().toString();
                String contrasena = editTextContrasena.getText().toString();
                if (correo == null || correo.isEmpty() || contrasena == null || contrasena.isEmpty() ){
                    Toast.makeText(view.getContext(), "RELLENE LOS FORMULARIOS", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cliente cliente = new Cliente();
                cliente.setCorreo(correo);
                cliente.setContrasena(contrasena);
                loginViewModel.pasarCredenciales(cliente);
            }
        });

        textViewRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections navDirections = LoginFragmentDirections.actionLoginToRegistro();
                Navigation.findNavController(requireView()).navigate(navDirections);
            }
        });
        //OBSERVER
        loginViewModel.getConfirmacion().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Toast.makeText(getContext(), "USUARIO CONFIMADO", Toast.LENGTH_SHORT).show();
                    NavDirections navDirections = LoginFragmentDirections.actionLoginToActivityApp();
                    Navigation.findNavController(requireView()).navigate(navDirections);
                }else{
                    Toast.makeText(getContext(), "USUARIO NO ENCONTRADO, POR FAVOR REGISTRESE", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  view;
    }





}