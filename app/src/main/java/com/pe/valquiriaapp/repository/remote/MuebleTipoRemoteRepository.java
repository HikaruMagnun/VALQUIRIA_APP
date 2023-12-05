package com.pe.valquiriaapp.repository.remote;

import com.pe.valquiriaapp.model.MueblesTipo;
import com.pe.valquiriaapp.repository.remote.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuebleTipoRemoteRepository {
    Connection connection;

    public MuebleTipoRemoteRepository() {
        connection= Conexion.conectar();

    }

    public List<MueblesTipo> listarMueblesTipo(){
        List<MueblesTipo> mueblesTipoList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select *  from muebles_tipo;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MueblesTipo mueblesTipo = new MueblesTipo();
                mueblesTipo.setTipo(resultSet.getString("tipo"));
                mueblesTipo.setImagen(resultSet.getString("imagen"));
                mueblesTipoList.add(mueblesTipo);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return mueblesTipoList;

    }


}
