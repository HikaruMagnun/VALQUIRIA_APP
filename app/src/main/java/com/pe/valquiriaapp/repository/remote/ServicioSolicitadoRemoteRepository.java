package com.pe.valquiriaapp.repository.remote;

import com.pe.valquiriaapp.model.Alojamiento;
import com.pe.valquiriaapp.model.ServicioSolicitado;
import com.pe.valquiriaapp.repository.remote.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioSolicitadoRemoteRepository {
    Connection connection;

    public ServicioSolicitadoRemoteRepository() {
        connection= Conexion.conectar();

    }

    public boolean agregarServicioSolicitado(ServicioSolicitado servicioSolicitado) {

        String sql = "INSERT INTO public.servicios_solicitados (id_servicio, id_alojamiento, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, servicioSolicitado.getServicio().getId());
            statement.setInt(2, servicioSolicitado.getAlojamiento().getId());
            statement.setInt(3, servicioSolicitado.getCantidad());

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
        e.printStackTrace();
            System.out.println(e);
        return false;
    }
}


}
