package com.pe.valquiriaapp.repository.remote;

import com.pe.valquiriaapp.model.Mueble;
import com.pe.valquiriaapp.repository.remote.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuebleRemoteRepository {
    Connection connection;

    public MuebleRemoteRepository() {
        connection= Conexion.conectar();

    }

    public List<Mueble> listMueblesPorTipo (String tipo){
        List<Mueble> muebles = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select *  from muebles where tipo = ?;");
            statement.setString(1,tipo);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Mueble mueble = new Mueble();
                mueble.setId(resultSet.getInt("id"));
                mueble.setDescription(resultSet.getString("descripcion"));
                mueble.setImagen(resultSet.getString("imagen"));
                mueble.setPrecio(resultSet.getFloat("precio"));
                muebles.add(mueble);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return muebles;

    }


}
