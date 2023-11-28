package com.pe.valquiriaapp.repository.remote;

import com.pe.valquiriaapp.model.Alojamiento;
import com.pe.valquiriaapp.model.Habitacion;
import com.pe.valquiriaapp.repository.remote.conexion.Conexion;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlojamientoRemoteRepository {
    Connection connection;

    public AlojamientoRemoteRepository() {
        connection= Conexion.conectar();

    }

    public boolean agregarAlojamiento(Alojamiento alojamiento) {
        if (!validarAlojamiento(alojamiento)){
            return false;
        }
        try {
            String sql = "INSERT INTO alojamientos (id_cliente, id_habitacion, id_empleado, fecha_alojamiento, fecha_alojamiento_vencimiento, estado_reserva, comentario) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setInt(1, alojamiento.getCliente().getDni());
            statement.setInt(1,12345678);
            statement.setInt(2, alojamiento.getHabitacion().getCodigoHabitacion());
            //statement.setInt(3, alojamiento.getEmpleado().getDni());
            statement.setInt(3, 1);

            statement.setDate(4, alojamiento.getFechaAlojamiento());
            statement.setDate(5, alojamiento.getFechaAlojamientoVencimiento());
            statement.setString(6, alojamiento.getEstadoReserva());
            statement.setString(7, alojamiento.getComentario());

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean validarAlojamiento(Alojamiento alojamiento){

        return true;
    }
}
