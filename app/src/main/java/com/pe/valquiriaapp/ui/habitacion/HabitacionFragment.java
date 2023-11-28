package com.pe.valquiriaapp.ui.habitacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pe.valquiriaapp.R;

public class HabitacionFragment extends Fragment {

    private HabitacionFragment binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_habitacion,container,false);

       return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}