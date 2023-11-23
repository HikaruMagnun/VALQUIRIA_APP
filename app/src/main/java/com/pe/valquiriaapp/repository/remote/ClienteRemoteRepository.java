package com.pe.valquiriaapp.repository.remote;

import com.pe.valquiriaapp.model.Cliente;
import com.pe.valquiriaapp.repository.remote.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRemoteRepository {
    Connection connection;

    public ClienteRemoteRepository() {
        connection= Conexion.conectar();
    }

    public boolean autenticarCliente(Cliente cliente) {
        String sql = "select dni from public.clientes where correo = ? and contrasena = ?; ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cliente.getCorreo());
            preparedStatement.setString(2, cliente.getContrasena());

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertarCliente(Cliente cliente) {
            String sql = "INSERT INTO clientes (dni, nombre, apellido, contrasena, correo) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1,cliente.getDni());
                preparedStatement.setString(2, cliente.getNombre());
                preparedStatement.setString(3, cliente.getApellido());
                preparedStatement.setString(4, cliente.getContrasena());
                preparedStatement.setString(5, cliente.getCorreo());

                int filasAfectadas = preparedStatement.executeUpdate();
                return filasAfectadas > 0;
            }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
