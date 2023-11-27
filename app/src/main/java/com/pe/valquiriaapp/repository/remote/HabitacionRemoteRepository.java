package com.pe.valquiriaapp.repository.remote;

import com.pe.valquiriaapp.model.Habitacion;
import com.pe.valquiriaapp.repository.remote.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class HabitacionRemoteRepository {
    Connection connection;

    public HabitacionRemoteRepository() {
        connection= Conexion.conectar();

    }

    public List<Habitacion> ListarHabitaciones(){
        List<Habitacion> listaProducto = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select* from habitaciones;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setCodigoHabitacion(resultSet.getInt("codigo_habitacion"));
                habitacion.setPiso(resultSet.getInt("piso"));
                habitacion.setPrecioDia(resultSet.getFloat("precio_dia"));
                habitacion.setTipo(resultSet.getString("tipo"));
                Array imagenes = resultSet.getArray("imagenes");
                habitacion.setImagenes((String[]) imagenes.getArray());
                habitacion.setDescripcion(resultSet.getString("description"));
                listaProducto.add(habitacion);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaProducto;



    }
}
