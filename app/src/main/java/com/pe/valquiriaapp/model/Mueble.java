package com.pe.valquiriaapp.model;

public class Mueble {
    int id;
    String description;
    String imagen;
    Float precio;
    public Mueble(){}

    public Mueble(int id, String description, String imagen, Float precio) {
        this.id = id;
        this.description = description;
        this.imagen = imagen;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
