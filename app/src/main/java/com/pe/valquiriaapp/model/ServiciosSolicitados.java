package com.pe.valquiriaapp.model;

public class ServiciosSolicitados {
    private int id;
    private Servicio servicio;
    private Alojamiento alojamiento;
    private boolean estado;

    private int cantidad;

    public ServiciosSolicitados() {
    }

    public ServiciosSolicitados(int id, Servicio servicio, Alojamiento alojamiento, boolean estado, int cantidad) {
        this.id = id;
        this.servicio = servicio;
        this.alojamiento = alojamiento;
        this.estado = estado;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
