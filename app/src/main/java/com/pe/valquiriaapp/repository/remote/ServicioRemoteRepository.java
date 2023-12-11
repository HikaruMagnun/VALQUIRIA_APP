package com.pe.valquiriaapp.repository.remote;

import com.pe.valquiriaapp.model.Servicio;
import com.pe.valquiriaapp.repository.remote.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioRemoteRepository {
    Connection connection;

    public ServicioRemoteRepository() {
        connection= Conexion.conectar();

    }

    public List<Servicio> listServicioPorTipo(String tipo){
        List<Servicio> servicioList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from servicios where tipo_servicio  = ?;");
            statement.setString(1,tipo);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Servicio servicio = new Servicio();
                servicio.setId(resultSet.getInt("id"));
                servicio.setDescripcion(resultSet.getString("descripcion"));
                servicio.setPrecio(resultSet.getFloat("precio"));
                servicio.getTipoServicio().setTipo(resultSet.getString("tipo_servicio"));
                servicio.setImagenes((String[]) resultSet.getArray("imagen").getArray());
                servicioList.add(servicio);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return servicioList;

    }

    public Servicio servicioPorId(int id){
        Servicio servicio = new Servicio();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from servicios where id = ?;");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                servicio.setId(resultSet.getInt("id"));
                servicio.setDescripcion(resultSet.getString("descripcion"));
                servicio.setPrecio(resultSet.getFloat("precio"));
                servicio.getTipoServicio().setTipo(resultSet.getString("precio"));
                servicio.setImagenes((String[]) resultSet.getArray("imagen").getArray());
            }
        }catch (SQLException e){
            System.out.println(e);
            e.printStackTrace();
        }

        return servicio;
    }


}
