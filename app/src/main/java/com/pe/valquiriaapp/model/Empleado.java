package com.pe.valquiriaapp.model;

public class Empleado {
    private int dni;
    private String nombre;
    private String apellido;
    private Integer codigo;
    private String cargo;

    public Empleado(int dni, String nombre, String apellido, Integer codigo, String cargo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigo = codigo;
        this.cargo = cargo;
    }
    public Empleado(){}

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
