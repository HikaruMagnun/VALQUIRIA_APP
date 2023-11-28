package com.pe.valquiriaapp.model;


public class Habitacion {
    private int codigoHabitacion;
    private int piso;
    private float precioDia;
    private String tipo;
    private String[] imagenes ;

    private String descripcion;

    public Habitacion() {
    }

    public Habitacion(int codigoHabitacion, int piso, float precioDia, String tipo, String[] imagenes, String descripcion) {
        this.codigoHabitacion = codigoHabitacion;
        this.piso = piso;
        this.precioDia = precioDia;
        this.tipo = tipo;
        this.imagenes = imagenes;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
