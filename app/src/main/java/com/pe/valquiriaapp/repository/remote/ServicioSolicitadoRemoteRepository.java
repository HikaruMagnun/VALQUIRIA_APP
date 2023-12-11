package com.pe.valquiriaapp.repository.remote;

import com.pe.valquiriaapp.model.Alojamiento;
import com.pe.valquiriaapp.model.Mueble;
import com.pe.valquiriaapp.model.ServicioSolicitado;
import com.pe.valquiriaapp.repository.remote.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<ServicioSolicitado> listarServiciosSolicitadosPorIdAlojamiento(int id_alojamiento) {
        List<ServicioSolicitado> servicioSolicitados = new ArrayList<>();
        String sql = "select * from servicios_solicitados where id_alojamiento = ? ;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_alojamiento);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ServicioSolicitado servicioSolicitado = new ServicioSolicitado();
                servicioSolicitado.setId(resultSet.getInt("id"));
                servicioSolicitado.getServicio().setId(resultSet.getInt("id_servicio"));
                servicioSolicitado.getAlojamiento().setId(resultSet.getInt("id_alojamiento"));
                servicioSolicitado.setEstado(resultSet.getBoolean("estado"));
                servicioSolicitado.setCantidad(resultSet.getInt("cantidad"));
                servicioSolicitados.add(servicioSolicitado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return servicioSolicitados;
    }



}
