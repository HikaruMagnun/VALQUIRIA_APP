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
        String sql = "select nro_doc from public.clientes where correo = ? and contrasena = ?; ";
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
            String sql = "INSERT INTO clientes (nro_doc, nombre, apellido, contrasena, correo,numero_telefono,direccion) VALUES (?, ?, ?, ?, ?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1,cliente.getDni());
                preparedStatement.setString(2, cliente.getNombre());
                preparedStatement.setString(3, cliente.getApellido());
                preparedStatement.setString(4, cliente.getContrasena());
                preparedStatement.setString(5, cliente.getCorreo());
                preparedStatement.setInt(6,cliente.getNumeroTelefonico());
                preparedStatement.setString(7,cliente.getDirreccion());
                int filasAfectadas = preparedStatement.executeUpdate();
                return filasAfectadas > 0;
            }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cliente clientePorCorreo(String correo) {
        String sql = "select * from public.clientes where correo = ? ; ";
        Cliente cliente = new Cliente();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, correo);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            cliente.setDni(resultSet.getInt("nro_doc"));
            cliente.setNombre(resultSet.getString("nombre"));
            cliente.setApellido(resultSet.getString("apellido"));
            cliente.setCorreo(resultSet.getString("correo"));
            cliente.setNumeroTelefonico(resultSet.getInt("numero_telefono"));
            cliente.setDirreccion(resultSet.getString("dirrecion"));
            System.out.println(cliente);
            return cliente;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
