package com.pe.valquiriaapp.model;

public class Habitacion {
    private int codigoHabitacion;
    private int piso;
    private float precioDia;
    private String tipo;
    private String[] imagenes;

    public Habitacion() {
    }

    public Habitacion(int codigoHabitacion, int piso, float precioDia, String tipo, String[] imagenes) {
        this.codigoHabitacion = codigoHabitacion;
        this.piso = piso;
        this.precioDia = precioDia;
        this.tipo = tipo;
        this.imagenes = imagenes;
    }

    public int getCodigoHabitacion() {
        return codigoHabitacion;
    }

    public void setCodigoHabitacion(int codigoHabitacion) {
        this.codigoHabitacion = codigoHabitacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public float getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(float precioDia) {
        this.precioDia = precioDia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(String[] imagenes) {
        this.imagenes = imagenes;
    }
}
