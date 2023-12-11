package com.pe.valquiriaapp.model;

public class Servicio {
    private int id;
    private String descripcion;
    private float precio;
    private ServicioTipo tipoServicio;
    private String[] imagen;

    public Servicio() {
        tipoServicio = new ServicioTipo();
    }

    public Servicio(int id, String descripcion, float precio, ServicioTipo tipoServicio, String[] imagen) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipoServicio = tipoServicio;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public ServicioTipo getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(ServicioTipo tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String[] getImagen() {
        return imagen;
    }

    public void setImagenes(String[] imagen) {
        this.imagen = imagen;
    }
}
