package com.pe.valquiriaapp.model;

import java.sql.Date;

public class Alojamiento {
    private int id;
    private Cliente cliente;
    private Habitacion habitacion;
    private Empleado empleado;
    private Date fechaRealizadoAlojamiento;
    private Date fechaAlojamiento;
    private Date fechaAlojamientoVencimiento;
    private String estadoReserva;
    private String comentario;

    public Alojamiento() {
        cliente = new Cliente();
        habitacion = new Habitacion();
    }

    public Alojamiento(int id, Cliente cliente, Habitacion habitacion, Empleado empleado, Date fechaRealizadoAlojamiento, Date fechaAlojamiento, Date fechaAlojamientoVencimiento, String estadoReserva, String comentario) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.empleado = empleado;
        this.fechaRealizadoAlojamiento = fechaRealizadoAlojamiento;
        this.fechaAlojamiento = fechaAlojamiento;
        this.fechaAlojamientoVencimiento = fechaAlojamientoVencimiento;
        this.estadoReserva = estadoReserva;
        this.comentario = comentario;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Date getFechaRealizadoAlojamiento() {
        return fechaRealizadoAlojamiento;
    }

    public void setFechaRealizadoAlojamiento(Date fechaRealizadoAlojamiento) {
        this.fechaRealizadoAlojamiento = fechaRealizadoAlojamiento;
    }

    public Date getFechaAlojamiento() {
        return fechaAlojamiento;
    }

    public void setFechaAlojamiento(Date fechaAlojamiento) {
        this.fechaAlojamiento = fechaAlojamiento;
    }

    public Date getFechaAlojamientoVencimiento() {
        return fechaAlojamientoVencimiento;
    }

    public void setFechaAlojamientoVencimiento(Date fechaAlojamientoVencimiento) {
        this.fechaAlojamientoVencimiento = fechaAlojamientoVencimiento;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
