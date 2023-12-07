package com.pe.valquiriaapp.repository.remote;

import com.pe.valquiriaapp.model.MueblesTipo;
import com.pe.valquiriaapp.model.ServicioTipo;
import com.pe.valquiriaapp.repository.remote.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioTipoRemoteRepository {
    Connection connection;

    public ServicioTipoRemoteRepository() {
        connection= Conexion.conectar();

    }

    public List<ServicioTipo> listarMueblesTipo(){
        List<ServicioTipo> servicioTipos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from servicios_tipos;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ServicioTipo ServicioTipo = new ServicioTipo();
                ServicioTipo.setTipo(resultSet.getString("tipo"));
                ServicioTipo.setImagen(resultSet.getString("imagen"));
                servicioTipos.add(ServicioTipo);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return servicioTipos;

    }


}
