package com.pe.valquiriaapp.model;

public class ServicioTipo {
    private String tipo;
    private String imagen;

    public ServicioTipo(){}

    public ServicioTipo(String tipo, String imagen) {
        this.tipo = tipo;
        this.imagen = imagen;
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
}
