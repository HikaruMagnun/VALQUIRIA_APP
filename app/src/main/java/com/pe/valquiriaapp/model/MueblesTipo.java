package com.pe.valquiriaapp.model;

import java.util.ArrayList;
import java.util.List;

public class MueblesTipo {
    String tipo;
    String imagen;
    List<Mueble> muebleList = new ArrayList<>();

    public MueblesTipo(){}

    public MueblesTipo(String tipo, String imagen, List<Mueble> muebleList) {
        this.tipo = tipo;
        this.imagen = imagen;
        this.muebleList = muebleList;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Mueble> getMuebleList() {
        return muebleList;
    }

    public void setMuebleList(List<Mueble> muebleList) {
        this.muebleList = muebleList;
    }
}
